//package kost4place.aa.kz.kosta4place;
//
//import android.content.Context;
//import android.test.suitebuilder.annotation.MediumTest;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//
//import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
//import kost4place.aa.kz.kosta4place.utils.ConvertUtils;
//@RunWith(AndroidJUnit4.class)
//@MediumTest
//public class Test {
//    private Context instrumentationCtx;
//
//    @Before
//    public void setup() {
//        instrumentationCtx = InstrumentationRegistry.getContext();
//    }
//    @org.junit.Test
//    public void example() {
//        PlaceORM.getInstance(ApplicationProvider.getApplicationContext()).getPlaceByCategoryId("cafe")
//                .filter(localPlaces -> !localPlaces.isEmpty())
//                .toObservable()
//                .map(ConvertUtils::returnPlaceWithCategory);
//
//    }
//}
