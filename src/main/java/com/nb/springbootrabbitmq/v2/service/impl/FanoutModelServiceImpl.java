package com.nb.springbootrabbitmq.v2.service.impl;

import com.nb.springbootrabbitmq.v2.producer.FanoutModelProducer;
import com.nb.springbootrabbitmq.v2.service.FanoutModelService;
import com.nb.springbootrabbitmq.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lvxuan
 * @program:
 * @Date: 2022/7/2 17:18
 * @Version: 1.0
 * @motto: 而后乃将图南
 * @Description: des
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
@Service
public class FanoutModelServiceImpl implements FanoutModelService {
    @Autowired
    private FanoutModelProducer fanoutModelProducer;

    @Override
    public void send(OrderVO vo, String exchange) {
        for (int i = 1; i <= 10; i++) {
            vo.setNum(i);
            fanoutModelProducer.send(vo, exchange);
        }
    }
}
