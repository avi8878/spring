package com.avi.batch.processor;

import com.avi.batch.model.User;
import org.springframework.batch.item.ItemProcessor;

public class SaveDBProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User item) throws Exception {
        item.setName(" Hello "+ item.getName());
        System.out.println(item);
        Thread.sleep(100);
        return item;
    }
}
