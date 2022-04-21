package config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task.HelloWorldTasklet;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final HelloWorldTasklet helloWorldTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, HelloWorldTasklet helloWorldTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.helloWorldTasklet = helloWorldTasklet;
    }

    @Bean
    public Job helloworldJob(Step helloworldStep){
        return jobBuilderFactory.get("helloWorldJob")
                .flow(helloworldStep)
                .end()
                .build();
    }

    @Bean
    public Step helloWorldStep(){
        return stepBuilderFactory.get("helloWorldStep")
                .tasklet(helloWorldTasklet)
                .build();
    }
}
