package com.roominventory.roominventorysys.service;

import com.roominventory.roominventorysys.model.Block;
import com.roominventory.roominventorysys.repository.BlockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public Block addNewBlock(Block block) {
        System.out.println(block);
        return blockRepository.save(block);
    }

    // Get all blocks
    public List<Block> getBlocks() {
        return (List<Block>) blockRepository.findAll();
    }

    // Get block by ID
    public Optional<Block> getBlockById(Integer id) {
        return blockRepository.findById(id);
    }

    @Transactional
    public void updateBlock(Integer blockID, Character name,Integer maxRooms) {
        Block block = blockRepository.findById(blockID)
                .orElseThrow(() -> new IllegalArgumentException("Block with id " + blockID + " does not exist"));

        if(name != null && !name.equals(block.getName())) {
            block.setName(name);
        }

        if(maxRooms != null && !maxRooms.equals(block.getMaxRooms())) {
            block.setMaxRooms(maxRooms);
        }

        blockRepository.save(block);
    }

    // Delete block by ID
    public void deleteBlock(Integer id) {
        blockRepository.deleteById(id);
    }
}
