package service;

import dao.DaoFactory;
import dao.impl.ResultDao;
import entity.Result;

import java.util.List;

public class ResultService {
    private DaoFactory factory = DaoFactory.getFactory();
    private ResultDao resultDao = factory.getResultDao();
    private Long chatId;

    public ResultService(Long chatId) {
        this.chatId = chatId;
    }

    public int getHourId() {
        List<Result> results = resultDao.selectForToday(chatId);// TODO: 04.04.20 if no, if more than 6
        if (results.isEmpty()){
            return 1;
        }
        // TODO: 04.04.20 if several results get for this hour
        // TODO: 04.04.20 need to add hourId to DB
        return 0;
    }
}
