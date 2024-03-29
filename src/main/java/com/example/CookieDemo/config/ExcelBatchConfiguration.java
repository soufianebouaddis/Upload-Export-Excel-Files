package com.example.CookieDemo.config;


public class ExcelBatchConfiguration {
    /*
    private final JobBuilder jobBuilderFactory;
    private final StepBuilder stepBuilderFactory;

    public ExcelBatchConfiguration(JobBuilder jobBuilderFactory, StepBuilder stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job excelProcessingJob() {
        return jobBuilderFactory.get("excelProcessingJob")
                .start(importAndFormatStep())
                .next(exportStep())
                .build();
    }

    @Bean
    public Step importAndFormatStep() {
        return stepBuilderFactory.get("importAndFormatStep")
                .tasklet((contribution, chunkContext) -> {
                    String filePath = "path/to/your/excel/file.xlsx";
                    List<ModelExcel> formattedData = excelService.importAndFormatExcel(filePath);
                    chunkContext.getStepContext().getStepExecution().getExecutionContext().put("formattedData", formattedData);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("csv-step", jobRepository)
                .<MSTabcNEUser, MSTabcNEUser>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob() {
        return new JobBuilder("MSTabcNEUser", jobRepository)
                .start(step1())
                .build();
    }
    }



    */


}
