package com.example.job.jobtask.writer;

import com.example.mapper.SampleTagMapper;
import com.example.model.SampleTagModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class SampleWriter implements ItemWriter<SampleTagModel> {
    @Autowired
    private SampleTagMapper sampleTagMapper;

    @Override
    public void write(List<? extends SampleTagModel> items) throws Exception {
        final Optional<List<? extends SampleTagModel>> writeList = Optional.ofNullable(items);
        if (writeList.isPresent()) {
            writeList.get().stream().forEach(entity -> {
                try {
                    sampleTagMapper.insertSampleTag(entity);
                } catch (Exception e) {
                    log.error("SampleWriter ERROR", e);
                }
            });
        }
    }
}
