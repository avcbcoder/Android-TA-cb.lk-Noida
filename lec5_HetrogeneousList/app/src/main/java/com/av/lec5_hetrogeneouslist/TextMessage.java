package com.av.lec5_hetrogeneouslist;

import org.w3c.dom.Text;

/**
 * Created by Ankit on 25-06-2018.
 */

public class TextMessage {
    String text;

    public TextMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
