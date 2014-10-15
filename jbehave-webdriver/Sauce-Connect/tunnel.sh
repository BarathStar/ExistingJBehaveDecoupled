#!/bin/bash

HOSTNAME=`hostname -s`
HOSTNBR=${HOSTNAME: -2}
echo Starting tunnel for user swa_ci_${HOSTNBR}_vip${EXECUTOR_NUMBER}
KEY=`grep $HOSTNAME,$EXECUTOR_NUMBER, keys.txt | awk -F, '{print $3}'`
USER=swa_ci_${HOSTNBR}_vip${EXECUTOR_NUMBER}
echo $KEY > $WORKSPACE/key
echo $USER > $WORKSPACE/user
echo "with Fast Fail RegExps ..."
java -Dhttps.protocols="SSLv3,SSLv2Hello" -jar Sauce-Connect.jar --se-port off --fast-fail-regexps "cdns\\.brsrvr\\.com,invitemedia\\.com,adnxs\\.com,addthiscdn\\.com,intellicast\\.com,addthis\\.com,sharethis\\.com,akamai\\.net,creative\\.sbnation\\.com,d\\.southwest\\.com,s\\.southwest\\.com,atdmt\\.com,demdex\\.net,southwestairlines\\.tt\\.omtrdc\\.net,sb-ssl\\.google\\.com,fxfeeds\\.mozilla\\.com,\\.bbci\\.co\\.uk,\\.bbc\\.co\\.uk,www\\.orbitz\\.com,i-cdn\\.openx\\.com,ox-d\\.southwest\\.com,\\.2o7\\.net,safebrowsing\\.com,metrics\\.southwest\\.com,smetrics\\.southwest\\.com,javadl-esd-secure\\.oracle\\.com" -b $USER $KEY --readyfile /tmp/sauce-connect-$EXECUTOR_NUMBER.ready | awk '{ print "SAUCELABS_TUNNEL:", $0; fflush(); }' &

while [ ! -e /tmp/sauce-connect-$EXECUTOR_NUMBER.ready ]; do sleep 5; done


