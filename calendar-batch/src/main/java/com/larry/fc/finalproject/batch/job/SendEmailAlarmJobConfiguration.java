package com.larry.fc.finalproject.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

/**
 * @author Larry
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SendEmailAlarmJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private static final int CHUNK_SIZE = 3;

    @Bean
    public Job sendEmailAlarmJob(
            Step sendEngagementAlarmStep,
            Step sendScheduleAlarmStep
    ) {
        return jobBuilderFactory.get("sendEmailAlarmJob")
                .start(sendEngagementAlarmStep)
                .next(sendScheduleAlarmStep)
                .build();
    }

    @Bean
    public Step sendEngagementAlarmStep(
            ItemReader<SendMailBatchReq> sendEngagementAlarmReader,
            ItemWriter<SendMailBatchReq> sendEmailAlarmWriter
    ) {
        return stepBuilderFactory.get("sendEngagementAlarmStep")
                .<SendMailBatchReq, SendMailBatchReq>chunk(CHUNK_SIZE)
                .reader(sendEngagementAlarmReader)
                .writer(sendEmailAlarmWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step sendScheduleAlarmStep(
            ItemReader<SendMailBatchReq> sendScheduleAlarmReader,
            ItemWriter<SendMailBatchReq> sendEmailAlarmWriter
    ) {
        return stepBuilderFactory.get("sendScheduleAlarmStep")
                .<SendMailBatchReq, SendMailBatchReq>chunk(CHUNK_SIZE)
                .reader(sendScheduleAlarmReader)
                .writer(sendEmailAlarmWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<SendMailBatchReq> sendEngagementAlarmReader() {
        return new JdbcCursorItemReaderBuilder<SendMailBatchReq>()
                .fetchSize(CHUNK_SIZE)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
                .sql("select s.id, s.start_at, s.title, u.email as user_email\n" +
                        "from schedules s\n" +
                        "         left join engagements e on s.id = e.schedule_id\n" +
                        "         left join users u on u.id = e.attendee_id\n" +
                        "where s.start_at >= date_format(date_add(now(), interval 10 minute)" +
                        ", '%Y-%m-%d %H:%i')\n" +
                        "  and s.start_at < date_format(date_add(now(), interval 11 minute)," +
                        " '%Y-%m-%d %H:%i')\n" +
                        "  and e.status = 'ACCEPTED'")
                .name("jdbcCursorItemReader")
                .build();
    }

    @Bean
    public JdbcCursorItemReader<SendMailBatchReq> sendScheduleAlarmReader() {
        return new JdbcCursorItemReaderBuilder<SendMailBatchReq>()
                .fetchSize(CHUNK_SIZE)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
                .sql("select s.id, s.start_at, s.title, u.email as user_email\n" +
                        "from schedules s\n" +
                        "    left join users u on u.id = s.writer_id\n" +
                        "where s.start_at >= date_format(date_add(now(), interval 10 minute)" +
                        ", '%Y-%m-%d %H:%i')\n" +
                        "  and s.start_at < date_format(date_add(now(), interval 11 minute)," +
                        " '%Y-%m-%d %H:%i')")
                .name("jdbcCursorItemReader")
                .build();
    }

    @Bean
    public ItemWriter<SendMailBatchReq> sendEmailAlarmWriter() {
        return list -> list.forEach(System.out::println);
    }
}
