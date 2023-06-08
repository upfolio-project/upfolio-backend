package com.up.upfolio.controllers;

import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.services.media.PhotoUploaderService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PhotoUploadController extends BaseController {
    private final PhotoUploaderService photoUploaderService;

    @PostMapping(value = "/profile/uploadPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessResponse uploadProfilePhoto(@Parameter(hidden = true) UUID userUuid,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam("cropX") Integer cropX,
                                              @RequestParam("cropY") Integer cropY,
                                              @RequestParam("side") Integer side) throws IOException {

        return photoUploaderService.updateProfilePhoto(userUuid, file.getInputStream(), file.getSize(), cropX, cropY, side);
    }
}
