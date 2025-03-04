package com.roominventory.roominventorysys.controller;

import com.roominventory.roominventorysys.model.Block;
import com.roominventory.roominventorysys.service.BlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/blocks")
public class BlockController {
    private final BlockService blockService;

    public BlockController(BlockService blockService){
        this.blockService = blockService;
    }

    @GetMapping
    public List<Block> getBlocks(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Optional<Block> block = blockService.getBlockById(id);

            if (block.isPresent()) {
                return List.of(block.get());
            }
            else {
                return new ArrayList<>();
            }
        }

        return blockService.getBlocks();
    }

    @PostMapping
    public ResponseEntity<Block> registerNewBlock(@RequestBody Block block) {
        try {
            Block addedBlock = blockService.addNewBlock(block);
            Optional<Block> finalBlock = blockService.getBlockById(addedBlock.getId());
            return new ResponseEntity<>(finalBlock.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteBlock(@RequestParam Integer id) {
        Optional<Block> block = blockService.getBlockById(id);

        if (block.isPresent()) {
            blockService.deleteBlock(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{blockID}")
    public ResponseEntity<String> updateBlock(
            @PathVariable("blockID") Integer blockID,
            @RequestParam(required = false) Character name,
            @RequestParam(required = false) Integer maxRooms) {

        // Ensure at least one field is provided
        if (blockID == null && name == null && maxRooms == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        blockService.updateBlock(blockID, name, maxRooms);
        return new ResponseEntity<>("Block updated successfully", HttpStatus.OK);
    }
}
