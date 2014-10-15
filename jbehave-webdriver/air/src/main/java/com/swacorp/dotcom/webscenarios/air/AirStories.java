package com.swacorp.dotcom.webscenarios.air;

import builders.ReservationSpecificationBuilder;
import com.swacorp.dotcom.webscenarios.air.util.MyWebDriverScreenshotOnFailure;
import com.thoughtworks.xstream.XStream;
import fixture.stubs.DynaStubsIntegration;
import groovy.lang.MetaClass;
import org.codehaus.plexus.util.SelectorUtils;
import org.codehaus.plexus.util.StringUtils;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.MetaFilter;
import org.jbehave.core.embedder.PrintStreamEmbedderMonitor;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.*;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.pico.PicoStepsFactory;
import org.jbehave.web.selenium.*;
import org.jboss.byteman.agent.submit.Submit;
import org.openqa.selenium.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.picocontainer.*;
import org.picocontainer.behaviors.Storing;
import org.picocontainer.classname.ClassLoadingPicoContainer;
import org.picocontainer.classname.ClassName;
import org.picocontainer.classname.DefaultClassLoadingPicoContainer;
import org.picocontainer.injectors.CompositeInjection;
import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.injectors.SetterInjection;
import org.picocontainer.injectors.SetterInjector;
import org.picocontainer.lifecycle.NullLifecycleStrategy;
import org.picocontainer.lifecycle.StartableLifecycleStrategy;
import org.picocontainer.monitors.NullComponentMonitor;
import state.CreditCardToUse;
import state.RRUserQueue;
import state.SWAContextView;
import state.UserPageContext;
import steps.conditional.ToggleMetrics;
import util.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.swacorp.dotcom.webscenarios.air.config.Domains.getDotcomDomain;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.XML;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

public class AirStories extends JUnitStories {
    public static final WatchDogFinalTrigger watchdogTrigger = new WatchDogFinalTrigger();
    public static final String PICO_TRACE = "pico.trace";
    private static final boolean TRACE_PICO = System.getProperty(PICO_TRACE) != null;
    public static final boolean FAULT_TESTING = System.getProperty("fault.testing") != null;
    private static int SCREEN_CTR = 0;
    private static String DISPLAY_SCREEN = "";
    private static final int btmPort = System.getProperty("BTM_PORT") == null ? Submit.DEFAULT_PORT : Integer.valueOf(System.getProperty("BTM_PORT"));
    public static final Submit faultSubmit = new Submit(Submit.DEFAULT_ADDRESS, btmPort);

    private static final String _STORIES_SEPARATOR = ",";
    protected String[] includes;

    private WebDriverProvider driverProvider;

    final java.util.Map<String, String> storyMap = new HashMap<String, String>();

    private CrossReference crossReference = new CrossReference() {

        @Override
        protected XRefRoot newXRefRoot() {
            MyXRefRoot myXRefRoot = new MyXRefRoot();
            return myXRefRoot;
        }

        @Override
        public String getMetaFilter() {
            String computerName = "unknown host";
            try {
                computerName = InetAddress.getLocalHost().getHostName();
            } catch (IOException e) {
            }
            return metaFilter + (domainToTest == null ? "" : "\ntesting: " + domainToTest + "\nfrom: " + computerName);
        }

        @Override
        protected void aliasForXRefStory(XStream xstream) {
            xstream.alias("story", MyXrefStory.class);
        }

        @Override
        protected void aliasForXRefRoot(XStream xstream) {
            xstream.alias("xref", MyXRefRoot.class);
        }

    }.withJsonOnly().withOutputAfterEachStory(false);

    private SeleniumContext seleniumContext = new SeleniumContext() {

        ThreadLocal<String> currentScenario = new ThreadLocal<String>();

        @Override
        public String getCurrentScenario() {
            return currentScenario.get();
        }

        @Override
        public void setCurrentScenario(String currentScenario) {
            this.currentScenario.set(currentScenario);
        }
    };

    private ContextView contextView;
    boolean usingLocalScreen = false;

    private StepMonitor stepMonitor;
    private boolean shouldDoDryRun = false;
    private static Format[] formats = new Format[]{XML, WEB_DRIVER_HTML};
    private String metaFilter;
    private String domainToTest = getDotcomDomain();
    private RRUserQueue rrUserQueue;


    public AirStories() {

        if (FAULT_TESTING) {
            String threadCount = System.getProperty("threads");
            if (threadCount != null && Integer.valueOf(threadCount) > 1) {
                System.out.println("################################################");
                System.out.println("Fault Injection testing must be single threaded!");
                System.out.println("   hint : -Dthreads=1                           ");
                System.out.println("################################################");
                System.exit(666);
            }
        }
        contextView = new ContextView.NULL();
        rrUserQueue = new RRUserQueue(hangarToUse());
        if (System.getProperty("dry-run") != null) {
            shouldDoDryRun = true;
            driverProvider = new TypeWebDriverProvider(DryRunWebDriver.class);
            stepMonitor = crossReference.getStepMonitor();
        } else if (System.getProperty("perf") != null) {
            driverProvider = new PerformanceWebDriverProvider();
            stepMonitor = crossReference.getStepMonitor();
        } else {
            init();
            contextView = createSWAContextView(usingLocalScreen);
            stepMonitor = new SeleniumStepMonitor(contextView, seleniumContext, crossReference.getStepMonitor());
            if (FAULT_TESTING) {
                new ToggleMetrics().metricsToggleOn();
            }
        }

        Class<?> embeddableClass = this.getClass();

        Configuration configuration = new SeleniumConfiguration()
                .useWebDriverProvider(driverProvider)
                .useSeleniumContext(seleniumContext)
                .usePendingStepStrategy(shouldDoDryRun ? new PassingUponPendingStep() : new FailingUponPendingStep())
                .useParameterConverters(new ParameterConverters(true))
                .useFailureStrategy(new RethrowingFailure())
                .doDryRun(shouldDoDryRun)
                .useStepMonitor(stepMonitor)
                .useStoryLoader(new LoadFromClasspath(embeddableClass.getClassLoader()))
                .useStoryReporterBuilder(
                        new MyStoryReporterBuilder(seleniumContext, contextView)
                                .withCrossReference(crossReference)
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats()
                                .withFailureTrace(true)
                                .withFailureTraceCompression(true)
                                .withFormats(formats))
        .useStoryControls(new StoryControls().doIgnoreMetaFiltersIfGivenStory(true));;
        useConfiguration(configuration);

        final ComponentMonitor swaComponentMonitor = new NullComponentMonitor() {

            @Override
            public <T> Constructor<T> instantiating(PicoContainer container, ComponentAdapter<T> componentAdapter, Constructor<T> constructor) {
                return super.instantiating(container, componentAdapter, constructor);
            }

            @Override
            public <T> void instantiationFailed(PicoContainer container, ComponentAdapter<T> componentAdapter, Constructor<T> constructor, Exception e) {
                StringBuilder logMessage = new StringBuilder(PICO_TRACE).append(" ").append(SWARemoteWebDriver.screenThreadLabel()).append(SWARemoteWebDriver.DELIM_LEFT)
                        .append(" instantiationFailed ").append(constructor.getName()).append(" ").append(e.getMessage())
                        .append(SWARemoteWebDriver.DELIM_END);
                StoryCommandLogger commandLogger = SWAContextView.retrievePageContext().storyCommandLogger;
                if (null == commandLogger) System.out.print(logMessage.toString());
                else commandLogger.writeLogMessage(logMessage.toString());
                super.instantiationFailed(container, componentAdapter, constructor, e);
            }

            @Override
            public <T> void instantiated(PicoContainer container, ComponentAdapter<T> componentAdapter, Constructor<T> constructor, Object instantiated, Object[] injected, long duration) {
                StringBuilder logMessage = new StringBuilder(PICO_TRACE).append(" ").append(SWARemoteWebDriver.screenThreadLabel()).append(SWARemoteWebDriver.DELIM_LEFT)
                        .append(" instantiated ").append(constructor.getName()).append(" ").append(System.identityHashCode(instantiated))
                        .append(SWARemoteWebDriver.DELIM_END);
                StoryCommandLogger commandLogger = SWAContextView.retrievePageContext().storyCommandLogger;
                if (null == commandLogger) System.out.print(logMessage.toString());
                else commandLogger.writeLogMessage(logMessage.toString());
                super.instantiated(container, componentAdapter, constructor, instantiated, injected, duration);
            }

            @Override
            public Object invoking(PicoContainer container, ComponentAdapter<?> componentAdapter, Member member, Object instance, Object[] args) {
                return super.invoking(container, componentAdapter, member, instance, args);
            }

            @Override
            public void invoked(PicoContainer container, ComponentAdapter<?> componentAdapter, Member member, Object instance, long duration, Object[] args, Object retVal) {
                StringBuilder logMessage = new StringBuilder(PICO_TRACE).append(" ").append(SWARemoteWebDriver.screenThreadLabel()).append(SWARemoteWebDriver.DELIM_LEFT)
                        .append(" invoked ").append(System.identityHashCode(instance)).append(".").append(member.getDeclaringClass()).append(".").append(member.getName())
                        .append(SWARemoteWebDriver.DELIM_END);
                StoryCommandLogger commandLogger = SWAContextView.retrievePageContext().storyCommandLogger;
                if (null == commandLogger) System.out.print(logMessage.toString());
                else commandLogger.writeLogMessage(logMessage.toString());
                super.invoked(container, componentAdapter, member, instance, duration, args, retVal);
            }

            @Override
            public void invocationFailed(Member member, Object instance, Exception e) {
                StringBuilder logMessage = new StringBuilder(PICO_TRACE).append(" ").append(SWARemoteWebDriver.screenThreadLabel()).append(SWARemoteWebDriver.DELIM_LEFT)
                        .append(" invocationFailed ").append(System.identityHashCode(instance)).append(".").append(member.getDeclaringClass()).append(".").append(member.getName()).append(" ").append(e.getMessage())
                        .append(SWARemoteWebDriver.DELIM_END);
                StoryCommandLogger commandLogger = SWAContextView.retrievePageContext().storyCommandLogger;
                if (null == commandLogger) System.out.print(logMessage.toString());
                else commandLogger.writeLogMessage(logMessage.toString());
                super.invocationFailed(member, instance, e);
            }

            @Override
            public void lifecycleInvocationFailed(MutablePicoContainer container, ComponentAdapter<?> componentAdapter, Method method, Object instance, RuntimeException cause) {
                StringBuilder logMessage = new StringBuilder(PICO_TRACE).append(" ").append(SWARemoteWebDriver.screenThreadLabel()).append(SWARemoteWebDriver.DELIM_LEFT)
                        .append(" lifecycleInvocationFailed ").append(System.identityHashCode(instance)).append(".").append(method.getDeclaringClass()).append(".").append(method.getName()).append(" ").append(cause.getMessage())
                        .append(SWARemoteWebDriver.DELIM_END);
                StoryCommandLogger commandLogger = SWAContextView.retrievePageContext().storyCommandLogger;
                if (null == commandLogger) System.out.print(logMessage.toString());
                else commandLogger.writeLogMessage(logMessage.toString());
                super.lifecycleInvocationFailed(container, componentAdapter, method, instance, cause);
            }

            @Override
            public Object noComponentFound(MutablePicoContainer container, Object componentKey) {
                return super.noComponentFound(container, componentKey);
            }

            @Override
            public Injector newInjector(Injector injector) {
                return super.newInjector(injector);
            }

            @Override
            public Behavior newBehavior(Behavior behavior) {
                return super.newBehavior(behavior);
            }
        };


        // For components at this level, there is one instance created per Story.
        // It is shared between all scenarios and steps for that story
        // ======================================================================
        final Storing statefulStore = new Storing(); // thread local magic
        ClassLoadingPicoContainer statefulThings = new DefaultClassLoadingPicoContainer(statefulStore.wrap(new ConstructorInjection()));
        statefulThings.addComponent(java.lang.String.class);
        statefulThings.addComponent(state.CurrentHotelPNR.class);
        statefulThings.addComponent(state.WebReferralOnOrOff.class);
        statefulThings.addComponent(state.ToggleCaptchaOff.class);
        statefulThings.addComponent(state.RewardsTransferOnOrOff.class);
        statefulThings.addComponent(state.FailOnAirTranContentOnOrOff.class);
        statefulThings.addComponent(state.Flow.class);
        statefulThings.addComponent(state.Trip.class);
        statefulThings.addComponent(state.DOTOnOrOff.class);
        statefulThings.addComponent(state.AirSelectAndPriceOnOrOff.class);
        statefulThings.addComponent(state.AirSelectOnOrOff.class);
        statefulThings.addComponent(state.NavitaireOnorOff.class);
        statefulThings.addComponent(CreditCardToUse.class);
        statefulThings.addComponent(util.ItineraryData.class);
        statefulThings.addComponent(util.RRContactInformation.class);
        statefulThings.addComponent(util.SelectFlightsPageData.class);
        statefulThings.addComponent(util.CustomerInfoData.class);
        statefulThings.addComponent(state.CarReservationData.class);
        statefulThings.addComponent(HotelItineraryData.class);
        statefulThings.addComponent(state.PassengerData.class);
        statefulThings.addComponent(state.ScenarioState.class);
        statefulThings.addComponent(RandomTicketTypeGroup.class);
        statefulThings.addComponent(state.RewardsTransferHistoryOnOrOff.class);
        statefulThings.addComponent(ReservationSpecificationBuilder.class);
        statefulThings.addComponent(util.ResetPasswordData.class);
        statefulThings.addComponent(state.AccountManagementData.class);
        statefulThings.addComponent(state.CompanyData.class);
        statefulThings.addComponent(util.DiscountData.class);
        statefulThings.addComponent(state.LastActivityDateOnOrOff.class);
        statefulThings.addComponent(util.PricePageData.class);
        statefulThings.addComponent(PromoDiscountData.class);
        statefulThings.addComponent(PageName.class);
        statefulThings.addComponent(BillingData.class);
        statefulThings.addComponent(PurchasePageData.class);

        // These are "managed single instance" and are all usable concurrently by many Stories.
        // don't make them hold per-story state !!!!!!
        // ===================================================================================

        final Storing pageStore = new Storing();
        final DefaultClassLoadingPicoContainer pagesAndComponents = new DefaultClassLoadingPicoContainer(
                pageStore.wrap(
                        new CompositeInjection(
                                new ConstructorInjection(),
                                new SetterInjection() {
                                    @Override
                                    public <T> ComponentAdapter<T> createComponentAdapter(ComponentMonitor monitor, LifecycleStrategy lifecycleStrategy, Properties componentProperties, Object componentKey, Class<T> componentImplementation, Parameter... parameters) throws PicoCompositionException {
                                        return new SetterInjector<T>(componentKey, componentImplementation, parameters, monitor, "set", "", false, true) {
                                            @Override
                                            protected boolean isInjectorMethod(Method method) {
                                                if (Modifier.isStatic(method.getModifiers())) {
                                                    return false;
                                                }
                                                if (method.getParameterTypes()[0].equals(DynaStubsIntegration.class)) {
                                                    return false;
                                                }
                                                if (method.getParameterTypes()[0].equals(WebElement.class) ||
                                                        method.getParameterTypes()[0].getName().matches("^.*Component$")) {
                                                    return false;
                                                }
                                                // Groovy classes have a setMetaClass(MetaClass mc) method which we don't want to inject into
                                                boolean b = method.getParameterTypes()[0] != MetaClass.class;
                                                return b && super.isInjectorMethod(method);
                                            }
                                        };
                                    }
                                })),
                new StartableLifecycleStrategy(TRACE_PICO ? swaComponentMonitor : new NullComponentMonitor()), statefulThings, AirStories.class.getClassLoader()
                , TRACE_PICO ? swaComponentMonitor : new NullComponentMonitor());
        pagesAndComponents.change(Characteristics.USE_NAMES).setName("pageObjects");
        pagesAndComponents.addComponent(WebDriverProvider.class, driverProvider);

        // This loads all the Groovy page objects
        addAllClassesToContainer(new ClassName("pages.HomePage"), true, pagesAndComponents);

        pagesAndComponents.addComponent(util.Navigation.class);
        pagesAndComponents.addComponent(util.BrowserActions.class);
        pagesAndComponents.addComponent(ReservationDataFactory.class);
        pagesAndComponents.addComponent(util.DatasetManager.class);

        final Storing stepsStore = new Storing();
        ComponentFactory delegate = new CompositeInjection(
                new ConstructorInjection(),
                new SetterInjection("set", "setMetaClass"));
        final ClassLoadingPicoContainer steps = new DefaultClassLoadingPicoContainer(
                stepsStore.wrap(delegate),
                new NullLifecycleStrategy(), pagesAndComponents, this.getClass().getClassLoader(), new NullComponentMonitor());
        steps.setName("stepsClasses");

        steps.addComponent(new CacheFlusher(statefulStore, pageStore, stepsStore));

        // This loads all the Groovy steps objects
        addAllClassesToContainer(new ClassName("steps.AirSearchSteps"), false, steps);
        addAllClassesToContainer(new ClassName("steps.thenSteps.RewardsTransferThenSteps"), false, steps);
        if (!shouldDoDryRun) {
            addAllClassesToContainer(new ClassName("steps.conditional.ToggleOnOffSteps"), false, steps);
        }
        if (System.getProperty("perf") != null) {
            steps.addComponent(steps.perf.PerfSteps.class);
            steps.addComponent(steps.perf.ProdPerfSteps.class);
        }
        steps.addComponent(new MyWebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
        steps.addComponent(new PerStoryWebDriverSteps(driverProvider));

        useStepsFactory(new PicoStepsFactory(configuration(), steps));

        pagesAndComponents.addComponent(hangarToUse());

        pagesAndComponents.addComponent(swabizEnvironmentToUse());
    }

    private void init() {
        if (System.getProperty("DISPLAY") != null) {
            System.out.println("LOCALHOST headless browsers (multi-threaded)");
            createDisplayPoolWebDriver();
        } else if (System.getProperty("REMOTE_WEBDRIVER_URL") != null) {
            System.out.println("REMOTE (or GRID) browsers");
            driverProvider = new RemoteWebDriverProvider() {
                DesiredCapabilities desiredCapabilities;

                @Override
                public void initialize() {
                    URL url = null;
                    WebDriver remoteWebDriver;
                    try {
                        url = createRemoteURL();
                        remoteWebDriver = new SWARemoteWebDriver(wrapCommandExecutor(new HttpCommandExecutor(url)), desiredCapabilities);
                    } catch (Throwable e) {
                        System.err.println("*********** Remote WebDriver Initialization Failure ************");
                        e.printStackTrace(System.err);
                        throw new UnsupportedOperationException("Connecting to remote URL '" + url + "' failed: " + e.getMessage(), e);
                    }

                    delegate.set(remoteWebDriver);

                }

                @Override
                protected DesiredCapabilities makeDesiredCapabilities() {
                    desiredCapabilities = super.makeDesiredCapabilities();
                    FirefoxProfile fireFoxProfile = new FirefoxProfile();
                    fireFoxProfile.setEnableNativeEvents(false);

                    String http_proxy = System.getProperty("HTTP_PROXY");
                    if (http_proxy != null) {
                        Proxy proxy = new Proxy();
                        proxy.setHttpProxy(http_proxy).setSslProxy(http_proxy);

                        System.out.println("HTTP_PROXY (" + http_proxy + ") detected and will be used in the browsers.");
                        fireFoxProfile.setProxyPreferences(proxy);
                    }

                    desiredCapabilities.setCapability("firefox_profile", fireFoxProfile);
                    return desiredCapabilities;
                }
            };
            StoryWatchDog.createShutDownHook();
        } else {
            System.out.println("LOCALHOST browsers (one at a time)");
            usingLocalScreen = true;
            driverProvider = new FirefoxWebDriverProvider() {
                public void initialize() {
                    super.initialize();
                }
            };
        }

        formats = new Format[]{CONSOLE, XML, WEB_DRIVER_HTML};
    }

    private static Data hangarToUse() {
        String hangar;

        hangar = System.getProperty("env");

        if (null != hangar) {
            if (hangar.equalsIgnoreCase("H1")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Hangar1 ****");
                System.out.println("******************************************************************************");
                return new SiebelQA334LoyaltyData();
            } else if (hangar.equalsIgnoreCase("H2")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Hangar2 ****");
                System.out.println("******************************************************************************");
                return new SiebelQA336LoyaltyData();
            } else if (hangar.equalsIgnoreCase("H3")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Hangar3 ****");
                System.out.println("******************************************************************************");
                return new SiebelQA432LoyaltyData();
            } else if (hangar.equalsIgnoreCase("H4")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Hangar4 ****");
                System.out.println("******************************************************************************");
                return new SiebelQA331LoyaltyData();
            } else if (hangar.equalsIgnoreCase("H5")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Hangar5 ****");
                System.out.println("******************************************************************************");
                return new SiebelQA2LoyaltyData();
            } else if (hangar.equalsIgnoreCase("efix")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against efix ****");
                System.out.println("******************************************************************************");
                return new SiebelDevLoyaltyData();
            } else if (hangar.equalsIgnoreCase("Jupiter")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Jupiter ****");
                System.out.println("******************************************************************************");
                return new SiebelDev6LoyaltyData();
            } else if (hangar.equalsIgnoreCase("Saturn")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Saturn ****");
                System.out.println("******************************************************************************");
                return new SiebelQA2LoyaltyData();
            } else if (hangar.equalsIgnoreCase("Uranus")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Uranus ****");
                System.out.println("******************************************************************************");
                return new SiebelDev1LoyaltyData();
            } else if (hangar.equalsIgnoreCase("Neptune")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against Neptune ****");
                System.out.println("******************************************************************************");
                return new SiebelDev1LoyaltyData();
            }
            System.out.println("******************************************************************************");
            System.out.println("**** Running against default Dev environment since invalid environment (" + hangar + ") was specified ****");
            System.out.println("******************************************************************************");
            return new SiebelDevLoyaltyData();
        } else {
            System.out.println("******************************************************************************");
            System.out.println("**** Running against default Dev environment since no environment was specified ****");
            System.out.println("******************************************************************************");
            return new SiebelDevLoyaltyData();
        }

    }

    private static Object swabizEnvironmentToUse() {
        String environment;

        environment = System.getProperty("functional.environment");
        if (null != environment) {
            if (environment.equalsIgnoreCase("qa")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against QA functional ****");
                System.out.println("******************************************************************************");
                return new SwaBizQaData();
            } else if (environment.equalsIgnoreCase("itst")) {
                System.out.println("******************************************************************************");
                System.out.println("**** Running against ITST functional ****");
                System.out.println("******************************************************************************");
                return new SwaBizDevData();
            }
            System.out.println("******************************************************************************");
            System.out.println("**** Running against default Dev functional environment since invalid environment (" + environment + ") was specified ****");
            System.out.println("******************************************************************************");
            return new SwaBizDevData();
        } else {
            System.out.println("******************************************************************************");
            System.out.println("**** Running against default Dev functional environment since no environment was specified ****");
            System.out.println("******************************************************************************");
            return new SwaBizDevData();
        }
    }

    private SWAContextView createSWAContextView(boolean usingLocalScreen) {
        SWAContextView swaContextView = new SWAContextView(driverProvider);
        swaContextView.usingLocalScreen = usingLocalScreen;
        swaContextView.initialize();
        return swaContextView;
    }

    private int getTimeOutFigure(int timeOutInt, String sysProp) {
        String timeOut = System.getProperty(sysProp);
        if (timeOut != null && !timeOut.equals("")) {
            try {
                timeOutInt = Integer.parseInt(timeOut);
            } catch (NumberFormatException e) {
                System.err.println(sysProp + " of " + timeOut + " badly formed, reverting to default of " + timeOutInt);
            }
        }
        return timeOutInt;
    }

    private void createDisplayPoolWebDriver() {
        DisplayIdPool displayIdPool = new DisplayIdPool(makeDisplays());
        driverProvider = new DisplayPoolingFirefoxWebDriverProvider(displayIdPool) {

            public void initialize() {
                super.initialize();
            }
        };
    }

    private String[] makeDisplays() {

        int MAX_DISPLAYS = 16;
        String displayValue = System.getProperty("DISPLAY");
        String displayIds[] = (String[]) Array.newInstance(String.class, MAX_DISPLAYS);
        if (displayValue != null) {
            for (int i = 0; i < MAX_DISPLAYS; i++) {
                displayIds[i] = ":" + displayValue + "." + (SCREEN_CTR++ % MAX_DISPLAYS);
            }
        }
        return displayIds;
    }

    private void addAllClassesToContainer(ClassName packageName, boolean recurse, final ClassLoadingPicoContainer container) {
        new DefaultClassLoadingPicoContainer().visit(packageName, ".*\\.class", recurse,
                new DefaultClassLoadingPicoContainer.ClassVisitor() {
                    public void classFound(@SuppressWarnings("rawtypes") Class clazz) {
                        if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()))
                            container.addComponent(clazz);
                            if (TRACE_PICO) {
                               StringBuilder logMessage =
                                    new StringBuilder(PICO_TRACE).append(" ")
                                                                 .append(SWARemoteWebDriver.screenThreadLabel())
                                                                 .append(SWARemoteWebDriver.DELIM_LEFT)
                                                                 .append(" Class loaded ").append(clazz.getName())
                                                                 .append(" ").append(System.identityHashCode(clazz))
                                                                 .append(SWARemoteWebDriver.DELIM_END);
                               StoryCommandLogger commandLogger = SWAContextView.retrievePageContext().storyCommandLogger;
                               if (null == commandLogger) System.out.print(logMessage.toString());
                               else commandLogger.writeLogMessage(logMessage.toString());
                            }
                    }
                });
    }

    @Override
    protected List<String> storyPaths() {
        final List<String> globList = storyGlob();
        System.out.println("******************************************************************************");
        System.out.println("**** globList = " + globList);
        System.out.println("******************************************************************************");
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(), globList, null);
    }

    private List<String> storyGlob() {
        final List<String> globList = new ArrayList<String>();
        final String[] globs = storyFilter().split(_STORIES_SEPARATOR);
        for (final String story : globs) {
            globList.add("**/" + (story.length() > 0 ? story : "*") + (story.endsWith(".story") ? "" : ".story"));
        }
        return globList;
    }


    protected List<String> storyPathFromJar(String storiesPath) throws IOException {
        try {
            return this.extractStoryNamesFromJar();
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        final List<String> globList = storyGlob();

        File file = new File(storiesPath);
        ArrayList<String> list = new ArrayList<String>();


        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.next();
            if (isIncluded(globList.toArray(new String[globList.size()]))) {
                list.add(line);
            }
        }
        s.close();
        return list;
    }

    private boolean isIncluded(String[] includes) {
        if (includes == null) {
            this.includes = null;
        } else {
            this.includes = new String[includes.length];
            for (int i = 0; i < includes.length; i++) {
                this.includes[i] = normalizePattern(includes[i]);
            }
        }
        return true;
    }

    private String normalizePattern(String pattern) {
        pattern = pattern.trim();

        if (pattern.startsWith(SelectorUtils.REGEX_HANDLER_PREFIX)) {
            if (File.separatorChar == '\\') {
                pattern = StringUtils.replace(pattern, "/", "\\\\");
            } else {
                pattern = StringUtils.replace(pattern, "\\\\", "/");
            }
        } else {
            pattern = pattern.replace(File.separatorChar == '/' ? '\\' : '/', File.separatorChar);

            if (pattern.endsWith(File.separator)) {
                pattern += "**";
            }
        }

        return pattern;
    }


    /**
     * set this on the command line -DstoryFilter=foo
     *
     * @return
     */
    private String storyFilter() {
        String storyFilter = System.getProperty("storyFilter");
        if (storyFilter == null) {
            storyFilter = "";
        }
        return storyFilter;
    }

    public static void main(String[] args) {
        try {
            AirStories airStories = new AirStories();
            airStories.run(airStories.extractStoryNamesFromJar());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private List<String> extractStoryNamesFromJar() throws IOException, URISyntaxException {
        Pattern p = Pattern.compile("^.*\\.story.*$");
        File jarFile = new File(AirStories.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        List<String> result = new ArrayList<String>();
        JarFile jar = new JarFile(jarFile);
        Enumeration<JarEntry> entryEnumeration = jar.entries();
        while (entryEnumeration.hasMoreElements()) {
            JarEntry jarEntry = entryEnumeration.nextElement();
            Matcher m = p.matcher(jarEntry.getName());
            if (m.find()) {
                String storyName = m.group();
                result.add(storyName);
            }
        }
        return result;
    }

    public void run(List<String> stories) throws IOException {
        Embedder embedder = configuredEmbedder();
        if (stories != null) {
            embedder.useMetaFilters(Arrays.asList(System.getProperty("meta.filter")));
            embedder.embedderControls().doIgnoreFailureInView(true);
            embedder.useEmbedderMonitor(new PrintStreamEmbedderMonitor() {
                @Override
                public void metaNotAllowed(Meta meta, MetaFilter filter){
                    // suppressing excluded story messages
                }
            });
        }
        metaFilter = super.configuredEmbedder().metaFilters().toString();
        System.out.println("****** Meta Filter: " + metaFilter);
        try {
            if (stories != null) {
                String timeout = System.getProperty("storyTimeoutInSecs");
                String threads = System.getProperty("threads");
                if (timeout != null) {
                    embedder.embedderControls().useStoryTimeoutInSecs(Long.valueOf(timeout));
                }
                if (threads != null) {
                    embedder.embedderControls().useThreads(Integer.valueOf(threads));
                }
                embedder.runStoriesAsPaths(stories);
            } else {
                embedder.runStoriesAsPaths(storyPaths());
            }
        } finally {
            embedder.generateCrossReference();
        }
    }

    @Override
    public void run() throws Throwable {
        run(null);
    }

    private static class MyStoryReporterBuilder extends StoryReporterBuilder {
        private final SeleniumContext seleniumContext;
        private ContextView contextView;

        public MyStoryReporterBuilder(SeleniumContext seleniumContext, ContextView contextView) {
            this.seleniumContext = seleniumContext;
            this.contextView = contextView;
        }

        @Override
        public StoryReporter reporterFor(String storyPath, org.jbehave.core.reporters.Format format) {
            if (System.getProperty("dry-run") == null) {
                updateContextStoryName(storyPath);
            }
            if (format == CONSOLE) {
                return new ConsoleOutput() {
                    @Override
                    public void beforeScenario(String title) {
                        seleniumContext.setCurrentScenario(title);
                        super.beforeScenario(title);
                    }

                    @Override
                    public void afterStory(boolean givenStory) {
                        UserPageContext pageContext = SWAContextView.retrievePageContext();
                        if (pageContext.videoCapturePid != null) {
                            ((SWARemoteWebDriver) pageContext.webDriverProvider.get()).ensureVideoStoppedAndOutputArtifactIndex();
                        }
                        contextView.close();
                        super.afterStory(givenStory);
                    }
                };
            } else {
                return super.reporterFor(storyPath, format);
            }
        }
    }

    private static void updateContextStoryName(String storyPath) {
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        if (null != pageContext) {
            pageContext.currentStory = null == storyPath ? "None" : storyPath.replace('/', '-');
            SWAContextView.replacePageContext(pageContext);
        }
    }

    private static class MyXRefRoot extends CrossReference.XRefRoot {
        private Set<String> processes = new HashSet<String>();
        private Set<String> projects = new HashSet<String>();
        private Set<String> flows = new HashSet<String>();
        private Set<String> travelers = new HashSet<String>();
        private Set<String> users = new HashSet<String>();

        @Override
        protected CrossReference.XRefStory createXRefStory(StoryReporterBuilder storyReporterBuilder, Story story, boolean passed, boolean pending) {
            return new MyXrefStory(story, storyReporterBuilder, passed, pending, MyXRefRoot.this.processes,
                    MyXRefRoot.this.projects, MyXRefRoot.this.flows, MyXRefRoot.this.travelers, MyXRefRoot.this.users);
        }

    }

    private static class MyXrefStory extends CrossReference.XRefStory {
        private String project; // used in JSON output
        private String process; // used in JSON output
        private String flow; // used in JSON output
        private String traveler; // used in JSON output
        private String jira;
        private boolean notPassing;
        private String user;
        private String suite;
        private transient Set<String> processes;
        private transient Set<String> projects;
        private transient Set<String> flows;
        private transient Set<String> travelers;
        private transient Set<String> users;

        public MyXrefStory(Story story, StoryReporterBuilder storyReporterBuilder, boolean passed, boolean pending, Set<String> processes,
                           Set<String> projects, Set<String> flows, Set<String> travelers, Set<String> users) {
            super(story, storyReporterBuilder, passed, pending);
            MyXrefStory.this.processes = processes;
            MyXrefStory.this.projects = projects;
            MyXrefStory.this.flows = flows;
            MyXrefStory.this.travelers = travelers;
            MyXrefStory.this.users = users;
        }

        @Override
        protected String appendMetaProperty(String property, String meta) {
            // turn meta tag into field - "project" only
            if (property.startsWith("project")) {
                this.project = extractValue(property);
                MyXrefStory.this.projects.add(this.project);
            } else if (property.startsWith("process")) {
                this.process = extractValue(property);
                MyXrefStory.this.processes.add(this.process);
            } else if (property.startsWith("flow")) {
                this.flow = extractValue(property);
                MyXrefStory.this.flows.add(this.flow);
            } else if (property.startsWith("traveler")) {
                this.traveler = extractValue(property);
                MyXrefStory.this.travelers.add(this.traveler);
            } else if (property.startsWith("user")) {
                this.user = extractValue(property);
                MyXrefStory.this.users.add(this.user);
            } else if (property.startsWith("suite")) {
                this.suite = extractValue(property);
            } else if (property.startsWith("not_passing")) {
                this.notPassing = true;
            } else if (property.startsWith("jira")) {
                this.jira = extractValue(property);
            } else {
                return super.appendMetaProperty(property, meta);
            }
            return null;
        }

        private String extractValue(String property) {
            return property.substring(property.indexOf("=") + 1);
        }

        @Override
        protected void addMetaProperty(String property, Set<String> meta) {

            // meta tabs should only be those not recognized
            if (!property.startsWith("suite")
                    && !property.startsWith("process")
                    && !property.startsWith("jira")
                    && !property.startsWith("traveler")
                    && !property.startsWith("project")
                    && !property.startsWith("story_id")
                    && !property.startsWith("flow")
                    && !property.startsWith("user")
                    && !property.startsWith("notPassing")) {
                super.addMetaProperty(property, meta);
            }
        }
    }

    public static class WatchDogFinalTrigger {
        @AfterStories
        public void cleanUp() {
            System.out.println("###### AfterStories Fired!  ");
            StoryWatchDog.cleanUp();
        }
    }

    public static class CacheFlusher {
        private final Storing statefulStore;
        private final Storing pageStore;
        private final Storing stepsStore;

        public CacheFlusher(Storing statefulStore, Storing pageStore, Storing stepsStore) {
            this.statefulStore = statefulStore;
            this.pageStore = pageStore;
            this.stepsStore = stepsStore;
        }

        @AfterStory(uponGivenStory = true)
        public void afterGivenStory() {
            popGivenStoryBoolean();
        }
        @AfterStory
        public void afterStory() {

            popGivenStoryBoolean();
            if (FAULT_TESTING) {
                System.out.println("thread." + Thread.currentThread().getId() + " Service tier running with injected byteman rules :");
                try {
                    System.out.println("thread." + Thread.currentThread().getId() + " " + AirStories.faultSubmit.listAllRules());
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        @BeforeStory(uponGivenStory = true)
        public void beforeGivenStory() {
            UserPageContext pageContext = SWAContextView.retrievePageContext();
            pageContext.isGivenStory.push(true);
            SWAContextView.replacePageContext(pageContext);
        }

        @BeforeStory
        public void beforeStory() {
            UserPageContext pageContext = SWAContextView.retrievePageContext();
            pageContext.isGivenStory.push(false);
            SWAContextView.replacePageContext(pageContext);
            resetStoryState();
        }

        private void resetStoryState() {
            statefulStore.resetCacheForThread();
            pageStore.resetCacheForThread();
            stepsStore.resetCacheForThread();
            if (FAULT_TESTING) {//TODO reconcile with flow indicator for fault testing
                try {
                    if (!AirStories.faultSubmit.listAllRules().startsWith("no")) {
                        AirStories.faultSubmit.deleteAllRules();
                    }
                    new JmxMetricsApi().resetMetrics();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void popGivenStoryBoolean() {
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        pageContext.isGivenStory.pop();
        SWAContextView.replacePageContext(pageContext);
    }

    public static class DryRunWebDriver implements WebDriver, JavascriptExecutor {
        public void get(String s) {
        }

        public String getCurrentUrl() {
            return null;
        }

        public String getTitle() {
            return null;
        }

        public List<WebElement> findElements(By by) {
            return null;
        }

        public WebElement findElement(By by) {
            return null;
        }

        public String getPageSource() {
            return null;
        }

        public void close() {
        }

        public void quit() {
        }

        public Set<String> getWindowHandles() {
            return null;
        }

        public String getWindowHandle() {
            return null;
        }

        public TargetLocator switchTo() {
            return null;
        }

        public Navigation navigate() {
            return null;
        }

        public Options manage() {
            return null;
        }

        public Object executeScript(String script, Object... args) {
            return null;
        }

        public Object executeAsyncScript(String script, Object... args) {
            return null;
        }
    }

}
