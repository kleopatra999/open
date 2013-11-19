package com.mapzen;

import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;

public class Tiles extends OnlineTileSourceBase {
    public static final String MAPBOX_KEY = "randyme.gajlngfe";
    private static String mapBoxBaseUrl = "http://api.tiles.mapbox.com/v3/";

    public Tiles() {
        super("mbtiles", ResourceProxy.string.base, 1, 20, 256, ".png", mapBoxBaseUrl);
    }

    @Override
    public String getTileURLString(MapTile mapTile) {
        StringBuffer url = new StringBuffer(getBaseUrl());
        url.append(MAPBOX_KEY);
        url.append("/");
        url.append(mapTile.getZoomLevel());
        url.append("/");
        url.append(mapTile.getX());
        url.append("/");
        url.append(mapTile.getY());
        url.append(".png");
        return url.toString();
    }
}
