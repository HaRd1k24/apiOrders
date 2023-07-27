package ru.lev.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lev.dao.ElectronicSigarsDao;
import ru.lev.model.Buyer;


@RestController
public class OrdersController {

    private ElectronicSigarsDao electronicSigarsDao;

    @Autowired
    public OrdersController(ElectronicSigarsDao electronicSigarsDao) {
        this.electronicSigarsDao = electronicSigarsDao;
    }

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String availableOrders(@RequestBody Buyer buyer) {
        return "Для заказа доступно: " + electronicSigarsDao.countTaste(buyer.getNameSigares());
    }

    @PostMapping(value = "/create/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String buyHQD(@RequestBody Buyer buyer) {
        return electronicSigarsDao.buyHqd(buyer.getNameSigares(), buyer.getCount());
    }

    @PostMapping(value = "/orders/zavoz", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putElectronicESsigars(@RequestBody Buyer buyer) {
        electronicSigarsDao.create(buyer.getNameSigares(), buyer.getCount());
        return "Вы завезли в базу:  " + buyer.getNameSigares() + " в количестве: " + buyer.getCount();
    }

    @PutMapping(value = "/orders/putZavoz",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String put(@RequestBody Buyer buyer){
        electronicSigarsDao.updateElectronigSigars(buyer.getNameSigares(),buyer.getCount());
        return "Вы завезли на базу: " + buyer.getNameSigares() + "кол-во:" + buyer.getCount();
    }

}
