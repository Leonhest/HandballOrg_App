package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

/**
 * Class to provide constants for different regions
 */
public enum Region {
    SOUTHERN_REGION("SouthernRegion"),
    EASTERN_REGION("EasternRegion"),
    NORTHERN_REGION("NorthernRegion"),
    WESTERN_REGION("WesternRegion"),
    SOUTH_WESTERN_REGION("SouthWesternRegion"),
    INLAND_REGION("InlandRegion");


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

        if (regionTxt.equalsIgnoreCase(SOUTHERN_REGION.regionTxt)) {
            return SOUTHERN_REGION;
        } else if (regionTxt.equalsIgnoreCase(WESTERN_REGION.regionTxt)) {
            return WESTERN_REGION;
        } else if (regionTxt.equalsIgnoreCase(NORTHERN_REGION.regionTxt)) {
            return NORTHERN_REGION;
        } else if (regionTxt.equalsIgnoreCase(EASTERN_REGION.regionTxt)) {
            return EASTERN_REGION;
        } else if (regionTxt.equalsIgnoreCase(SOUTH_WESTERN_REGION.regionTxt)) {
            return SOUTH_WESTERN_REGION;
        } else if (regionTxt.equalsIgnoreCase(INLAND_REGION.regionTxt)) {
            return INLAND_REGION;
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

        return region.equalsIgnoreCase(SOUTHERN_REGION.regionTxt) || region.equalsIgnoreCase(WESTERN_REGION.regionTxt)
                || region.equalsIgnoreCase(NORTHERN_REGION.regionTxt) || region.equalsIgnoreCase(EASTERN_REGION.regionTxt)
                || region.equalsIgnoreCase(SOUTH_WESTERN_REGION.regionTxt) || region.equalsIgnoreCase(INLAND_REGION.regionTxt);
    }
}
