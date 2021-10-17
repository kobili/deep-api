package com.deep.deep.service;

import com.deep.deep.dto.AddDeepBulkRequestDto;
import com.deep.deep.dto.AddDeepRequestDto;
import com.deep.deep.dto.DeepResponseDto;
import com.deep.deep.entity.Deep;
import com.deep.deep.repository.DeepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

@Service
public class DeepService {

    @Autowired
    private final DeepRepository deepRepository;

    @Autowired
    public DeepService(DeepRepository deepRepository) {
        this.deepRepository = deepRepository;
    }

    /**
     * fetches a Deep item from database and converts it to a DeepResponseDto
     * @return : a DeepResponseDto
     */
    public DeepResponseDto getRandomDeep() {
        List<Deep> allDeeps = (List<Deep>) deepRepository.findAll();
        int numberOfDeeps = allDeeps.size();

        if (numberOfDeeps == 0) {
            return new DeepResponseDto(69, "You've gotta be deeping me");
        }

        Deep randomDeep = allDeeps.get(ThreadLocalRandom.current().nextInt(0, numberOfDeeps));

        return new DeepResponseDto(randomDeep.getId(), randomDeep.getDeep());
    }

    /**
     * Returns a DeepResponseDto for the Deep entity with the given id
     * @param id: id of the Deep we want to retrieve
     */
    public DeepResponseDto getIndexedDeep(int id) {
        Optional<Deep> deep = deepRepository.findById(id);

        return deep.map(value -> new DeepResponseDto(value.getId(), value.getDeep()))
                .orElseGet(() -> new DeepResponseDto(420, "You're pretty deep"));
    }

    /**
     * Gets a list of all Deeps in the database
     */
    public List<DeepResponseDto> getAllDeeps() {
        List<DeepResponseDto> response = new ArrayList<>();
        List<Deep> allDeeps = (List<Deep>) deepRepository.findAll();

        allDeeps.forEach(deep -> response.add(new DeepResponseDto(deep.getId(), deep.getDeep())));

        return response;
    }

    /**
     * Adds a  new Deep entity to database
     * @param deepRequestDto : request body containing
     * @return a DeepResponseDto of the Deep entity that was just added
     */
    public DeepResponseDto addDeep(AddDeepRequestDto deepRequestDto) {
        Deep deepToAdd = new Deep();
        deepToAdd.setDeep(deepRequestDto.getDeep());

        deepRepository.save(deepToAdd);

        return new DeepResponseDto(deepToAdd.getId(), deepToAdd.getDeep());
    }

    /**
     * Takes a list of deep messages and calls addDeep to add them to database
     * @param deepBulkRequestDto: the request body
     * @return a list of DeepResponseDto each of which were generated by addDeep
     */
    public List<DeepResponseDto> addDeeps(AddDeepBulkRequestDto deepBulkRequestDto) {
        List<DeepResponseDto> response = new ArrayList<>();

        deepBulkRequestDto.getDeeps().forEach(deepString -> response.add(addDeep(new AddDeepRequestDto(deepString))));

        return response;
    }
}
