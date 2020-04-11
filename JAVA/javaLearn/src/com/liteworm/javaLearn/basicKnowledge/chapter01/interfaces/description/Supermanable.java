package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.description;

import com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.flayable.Flyable;
import com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.swimmable.Swimmable;

public interface Supermanable extends Flyable, Swimmable {
    void  battle();
}
