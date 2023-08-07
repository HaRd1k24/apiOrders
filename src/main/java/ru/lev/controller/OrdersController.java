package ru.lev.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lev.dao.ElectronicSigarsDaoImpl;
import ru.lev.model.Buyer;


@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final ElectronicSigarsDaoImpl electronicSigarsDao;


    @GetMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String availableOrders(@RequestBody Buyer buyer) {
        return "Для заказа доступно: " + electronicSigarsDao.countTaste(buyer.getNameSigares());
    }

    @PostMapping(value = "/v1/orders/buy", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String buyHQD(@RequestBody Buyer buyer) {
        return electronicSigarsDao.buyHqd(buyer.getNameSigares(), buyer.getCount());
    }

    @PostMapping(value = "/v1/orders/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putElectronicESsigars(@RequestBody Buyer buyer) {
        electronicSigarsDao.create(buyer.getNameSigares(), buyer.getCount());
        return "Вы завезли в базу:  " + buyer.getNameSigares() + " в количестве: " + buyer.getCount();
    }

    @PatchMapping(value = "/v1/orders/refreshCount",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String put(@RequestBody Buyer buyer){
        electronicSigarsDao.updateElectronigSigars(buyer.getNameSigares(),buyer.getCount());
        return "Вы завезли на базу: " + buyer.getNameSigares() + "кол-во:" + buyer.getCount();
    }

}
