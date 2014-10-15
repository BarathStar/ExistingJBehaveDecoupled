const buildUrl = '/hudson/job/Performance_Production_AirPurchase/api/json';

function getHowManyBuildsToDisplay() {
    if(document.location.search.length > 1) {
        var query = document.location.search.substr(1);
        var numberOfBuilds = parseInt(query, 10);
        if(numberOfBuilds !== NaN) {
            return numberOfBuilds;
        }
    }
    return defaultNumberOfBuildsToDisplay = 10;
}

var forEachBuildArtifact = function(regExp, callback) {
    $.getJSON(buildUrl, function(data) {
        var builds = data.builds
        $.each(builds.splice(0, getHowManyBuildsToDisplay()), function(buildIndex, buildReference) {
            var buildId = 'BUILD-' + buildReference.number;
            $.getJSON(buildReference.url + '/api/json', function(build) {
                $.each(build.artifacts, function(buildArtifactIndex, buildArtifactReference) {

                    if (buildArtifactReference.relativePath.match(regExp)) {
                        var artifactUrl = buildReference.url + 'artifact/' + buildArtifactReference.relativePath;
                        var artifactId = buildId + '-ARTIFACT-' + buildArtifactIndex;
                        $.getJSON(artifactUrl, function(buildArtifact) {
                            callback(buildIndex, artifactUrl, buildArtifact)
                        });
                    }
                });
            });
        });
    });
}

function makeChart(pageName) {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    return new Highcharts.Chart({
        chart: {
            renderTo: 'chart',
            zoomType: 'x'
        },
        global: {
            useUTC: false
        },
        title: {
            text: pageName
        },
        subtitle: {
            text: 'production page load times'
        },
        xAxis: {
            title: {
                text: 'Time'
            },
            type: 'datetime',
            minPadding: 0.2,
            maxPadding: 0.2,
            maxZoom: 4 * 3600000 // four hours
        },
        yAxis: {
            title: {
                text: 'Seconds'
            },
            minPadding: 0.2,
            maxPadding: 0.2,
            maxZoom: 60,
            plotLines: [
                {
                    value: 0,
                    width: 1,
                    color: '#808080'
                }
            ]
        },
        legend: {
            enabled: true
        },
        exporting: {
            enabled: false
        },
        series: [
            {
                name: 'onLoad',
                data: []
            },
            {
                name: 'allContentComplete',
                data: []
            }

        ],
        tooltip: {
            shared: true,
            crosshairs: true
        },
        plotOptions: {
            series: {
                point: {
                    cursor: 'pointer',
                    events: {
                        click: function(event) {
                            var clickedPoint = this;
                            window.open(clickedPoint.url);
                        }
                    }
                },
                marker: {
                    lineWidth: 1
                }
            }
        }
    });
};

var standardAddPoint = function(buildIndex, buildArtifactUrl, buildArtifact, chart) {
    if (buildArtifact.log.pages[0].pageTimings.onContentLoad > 0) {
        var startTime = Date.parse(buildArtifact.log.pages[0].startedDateTime);
        chart.series[0].addPoint({
            x: startTime,
            y: buildArtifact.log.pages[0].pageTimings.onContentLoad / 1000,
            url: buildArtifactUrl
        })

        var entries = buildArtifact.log.entries;
        var lastEntry = entries[entries.length - 1];
        var allContentLoadedTime = Date.parse(lastEntry.startedDateTime);
        var contentLoadedDuration = allContentLoadedTime - startTime;
        chart.series[1].addPoint({
            x: startTime,
            y: contentLoadedDuration / 1000,
            url: buildArtifactUrl
        })

    }
};
