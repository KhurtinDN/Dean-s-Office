package ru.sgu.csit.inoc.deansoffice.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.ParanamerConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import ru.sgu.csit.inoc.deansoffce.jbehave.steps.*;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

public class TestRunner extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new ParanamerConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder().withFormats(CONSOLE, HTML, XML, STATS));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(),
                new FirstSteps()
        ).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "");
    }
}
