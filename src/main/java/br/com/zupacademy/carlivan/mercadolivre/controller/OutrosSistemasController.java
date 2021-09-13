package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.NovaCompraNotaForm;
import br.com.zupacademy.carlivan.mercadolivre.controller.form.NovaCompraRankingForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@RequestBody @Valid NovaCompraNotaForm form) throws InterruptedException {

        System.out.println("Notas Fiscais!!!");
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid NovaCompraRankingForm form) throws InterruptedException {

        System.out.println("Rankings!!!");
        Thread.sleep(150);
    }
}
