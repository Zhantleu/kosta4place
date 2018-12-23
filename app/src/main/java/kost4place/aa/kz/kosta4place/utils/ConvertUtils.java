package kost4place.aa.kz.kosta4place.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kost4place.aa.kz.kosta4place.local.model.LocalCategory;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;
import kost4place.aa.kz.kosta4place.local.model.PlaceWithCategory;
import kost4place.aa.kz.kosta4place.remote.model.RemotePlace;

public class ConvertUtils {

    @NonNull
    private static List<LocalPlace> convertRemotePlaceToLocal(RemotePlace remotePlace) {
        List<LocalPlace> localPlaces = new LinkedList<>();
        localPlaces.add(new LocalPlace(remotePlace.getId(), remotePlace.getCategory().getId(), remotePlace.getPlaceTitle(), remotePlace.getInfo(), remotePlace.getLocation(), remotePlace.getUrlLocation()));
        return localPlaces;
    }

    @NonNull
    private static LocalCategory convertRemoteCategoryToLocal(RemotePlace remotePlace) {
        return new LocalCategory(remotePlace.getCategory().getId(), remotePlace.getCategory().getCategoryTitle());
    }

    public static List<PlaceWithCategory> convert(List<RemotePlace> remotePlace) {
        List<PlaceWithCategory> placeWithCategories = new LinkedList<>();

        for (RemotePlace place : remotePlace) {
            placeWithCategories.add(new PlaceWithCategory(convertRemoteCategoryToLocal(place), convertRemotePlaceToLocal(place)));
        }

        return placeWithCategories;
    }

    public static List<PlaceWithCategory> returnPlaceWithCategory(List<LocalPlace> localPlace) {
        List<PlaceWithCategory> placeWithCategories = new ArrayList<>();

        placeWithCategories.add(new PlaceWithCategory(new LocalCategory(), localPlace));

        return placeWithCategories;
    }

    public static List<LocalPlace> returnLocalFromPlaceWithCategory(List<PlaceWithCategory> placeWithCategories) {
        List<LocalPlace> localPlaces = new ArrayList<>();
        for (PlaceWithCategory placeWithCategory : placeWithCategories) {
            localPlaces.addAll(placeWithCategory.localPlaces);
        }

        return localPlaces;
    }
}
