package ru.sgu.csit.inoc.deansoffice.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.ParanamerConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import ru.sgu.csit.inoc.deansoffce.jbehave.steps.FirstSteps;

import java.util.List;

import static org.jbehave.core.reporters.Format.*;

public class SuperStory extends JUnitStory {

    @Override
    public Configuration configuration() {
        return new ParanamerConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder().withFormats(CONSOLE));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(),
                new FirstSteps()
        ).createCandidateSteps();
    }
}
