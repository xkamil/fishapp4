package com.example.fishapp.app.dictionary;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import com.example.fishapp.app.dictionary.dto.DictionaryUploadDto;
import com.example.fishapp.app.dictionary.repository.DictionaryQueryRepository;
import com.example.fishapp.app.dictionary.service.DictionaryCommandService;
import com.example.fishapp.app.dictionary.service.DictionaryQueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dictionaries")
public class DictionaryController {

    private final DictionaryCommandService dictionaryCommandService;
    private final DictionaryQueryService dictionaryQueryService;

    public DictionaryController(DictionaryCommandService dictionaryCommandService, DictionaryQueryService dictionaryQueryService) {
        this.dictionaryCommandService = dictionaryCommandService;
        this.dictionaryQueryService = dictionaryQueryService;
    }

    @GetMapping("/find")
    public Map<String, String> find(
            @RequestParam @NotEmpty String name,
            @RequestParam @NotEmpty String locale) {
        return dictionaryQueryService.findByNameAndLocale(name, locale);
    }

    @PutMapping(path = "/upload", consumes = {"multipart/form-data"})
    public String upload(
            @ModelAttribute @Valid DictionaryUploadDto dictionaryUploadDto) throws Exception {
        String fileContent = new String(dictionaryUploadDto.getDictionary().getBytes());
        String dictionaryName = dictionaryUploadDto.getDictionary().getOriginalFilename().replace(".csv", "");
        dictionaryCommandService.createDictionary(dictionaryName, fileContent);

        return fileContent;
    }

}
