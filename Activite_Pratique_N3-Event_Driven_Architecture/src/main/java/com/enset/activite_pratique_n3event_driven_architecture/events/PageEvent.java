package com.enset.activite_pratique_n3event_driven_architecture.events;

import java.util.Date;
public record PageEvent(String name, String user, Date date, long duration) {
}
