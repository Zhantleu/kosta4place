package kost4place.aa.kz.kosta4place.dao;

import java.util.List;

import kost4place.aa.kz.kosta4place.model.Place;

public interface DatabaseCallback {

    void onPlacesLoaded(List<Place> users);

    void onDataNotAvailable();

    void onPlaceAdded();

}