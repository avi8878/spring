package com.avi.batch.config;

import com.avi.batch.model.User;
import com.avi.batch.processor.SaveDBProcessor;
import com.avi.batch.writer.DBWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import java.io.IOException;
import java.util.concurrent.Executors;

@Configuration
@EnableBatchProcessing
@EnableJpaRepositories(basePackages = "com.avi.batch")
public class BatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepbuilder) throws IOException {

        Step  step = stepbuilder.get("batch-steps")
                .chunk(3)
                .reader(flatFileItemReader())
                .processor( itemProcessor())
                .writer(itemWriter())
                 .taskExecutor(threadPoolTaskExecutor())
                .build();


        return jobBuilderFactory.get("batch-demo")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();

    }@Bean
 public ConcurrentTaskExecutor threadPoolTaskExecutor(){

    /*    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(25);
        threadPoolTaskExecutor.initialize();*/
        ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor();
        concurrentTaskExecutor.setConcurrentExecutor(Executors.newFixedThreadPool(10));
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(5);
        return  concurrentTaskExecutor;
    }

    @Bean
    public LineMapper<User> lineMapper(){
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(new String[]{"id","name"});
        BeanWrapperFieldSetMapper<User> mapper = new BeanWrapperFieldSetMapper();
        mapper.setTargetType(User.class);
        defaultLineMapper.setFieldSetMapper(mapper);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        return  defaultLineMapper;
    }

    @Bean
    public FlatFileItemReader<User> flatFileItemReader(){

        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader();
        final String SOURCE = "E://batch//source.txt";
      flatFileItemReader.setResource(new FileSystemResource(SOURCE));
      flatFileItemReader.setName("file-reader");
      flatFileItemReader.setLinesToSkip(1);
      flatFileItemReader.setLineMapper(lineMapper());
      return flatFileItemReader;

    }

    @Bean
    public ItemProcessor itemProcessor(){
        return new SaveDBProcessor();
    }

    @Bean
    public ItemWriter itemWriter() throws IOException {
        return new DBWriter();
    }

}
