package br.com.zupacademy.carlivan.mercadolivre.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake{
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem -> "http://bucket.io/"+imagem
                .getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
