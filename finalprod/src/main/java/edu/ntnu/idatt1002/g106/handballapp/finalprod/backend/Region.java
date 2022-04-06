package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

/**
 * Class to provide constants for different regions
 */
public enum Region {
    SouthernRegion("SouthernRegion"),
    EasternRegion("EasternRegion"),
    NorthernRegion("NorthernRegion"),
    WesternRegion("WesternRegion");

    private String regionTxt;

    Region(String regionTxt) {
        this.regionTxt = regionTxt;
    }

    /**
     * Getter for the region text associated to the enum
     * @return Region text
     */
    public String getRegionTxt() {
        return regionTxt;
    }

    /**
     * Method to find a region hence on region text in the {@code param}
     * @param regionTxt Name of region
     * @return Region constant if match with parameter text, otherwise null
     */
    public static Region findRegion(String regionTxt) {
        if (!validRegion(regionTxt)) {
            return null;
        }

        if (regionTxt.equalsIgnoreCase(SouthernRegion.regionTxt)) {
            return SouthernRegion;
        } else if (regionTxt.equalsIgnoreCase(WesternRegion.regionTxt)) {
            return WesternRegion;
        } else if (regionTxt.equalsIgnoreCase(NorthernRegion.regionTxt)) {
            return NorthernRegion;
        } else if (regionTxt.equalsIgnoreCase(EasternRegion.regionTxt)) {
            return EasternRegion;
        } else {
            return null;
        }

    }

    /**
     * Method to check if a region is valid
     * @param region The region to check
     * @return {@code true} if the param region matches a registered region, otherwise {@code false}
     */
    private static boolean validRegion(String region) {
        if (region == null) {
            return false;
        }

        return region.equalsIgnoreCase(SouthernRegion.regionTxt) || region.equalsIgnoreCase(WesternRegion.regionTxt) || region.equalsIgnoreCase(NorthernRegion.regionTxt) || region.equalsIgnoreCase(EasternRegion.regionTxt);
    }
}
