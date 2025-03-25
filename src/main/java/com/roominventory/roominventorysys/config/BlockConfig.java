package com.roominventory.roominventorysys.config;

import com.roominventory.roominventorysys.model.Block;
import com.roominventory.roominventorysys.repository.BlockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BlockConfig {

    @Bean
    @Order(2)
    CommandLineRunner initializeBlocks(BlockRepository blockRepository) {
        return args -> {
            if (blockRepository.count() == 0) {
                System.out.println("Block Repository count was 0");
                List<Block> blockList = new ArrayList<>();
                blockList.add(new Block('A', 40));
                blockList.add(new Block('B', 40));
                blockList.add(new Block('C', 40));
                blockList.add(new Block('D', 40));
                blockList.add(new Block('E', 20));
                blockList.add(new Block('F', 20));
                blockList.add(new Block('G', 20));

                blockRepository.saveAll(blockList);
                System.out.println("Default block data initialized successfully.");
            }
            else {
                System.out.println("Block table already populated. Skipping initialization.");
            }
        };
    }
}
