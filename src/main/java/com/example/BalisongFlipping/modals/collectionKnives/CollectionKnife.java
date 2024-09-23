package com.example.BalisongFlipping.modals.collectionKnives;

import com.example.BalisongFlipping.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document("collectionKnives")
public class CollectionKnife {
    // knife values
    @Id
    private String id;
    private String collectionId;

    private String displayName;

    private String knifeMaker;
    private String baseKnifeModel;

    private KnifeType knifeType;

    private String aqquiredDate;
    private Date creationDate;

    private boolean isFavoriteKnife;
    private boolean isFavoriteFlipper;

    private String coverPhoto;

    private double msrp;
    private double overallLength;
    private double weight;
    private PivotSystem pivotSystem;
    private PinSystem pinSystem;
    private LatchType latchType;

    private boolean hasModularBalance;
    private String balanceValue;

    private BladeStyle bladeStyle;
    private BladeFinish bladeFinish;
    private BladeMaterial bladeMaterial;

    private HandleConstruction handleConstruction;
    private HandleFinish handleFinish;
    private HandleMaterial handleMaterial;

    private String averageScore;
    private String qualityScore;
    private String flippingScore;
    private String feelScore;
    private String soundScore;
    private String durabilityScore;

    // gallery images/videos
    List<String> galleryFiles;

    // state values
    private boolean hasBeenSold;
    private boolean wantToTrade;
    private boolean upForSale;
    private boolean noLongerHave;

    public CollectionKnife(
            String collectionId,
            String displayName,
            String knifeMaker,
            String baseKnifeModel,
            String knifeType,
            String aqquiredDate,
            String isFavoriteKnife,
            String isFavoriteFlipper,
            String coverPhoto,
            String knifeMSRP,
            String overallLength,
            String weight,
            String pivotSystem,
            String latchType,
            String pinSystem,
            String hasModularBalance,
            String balanceValue,
            String bladeStyle,
            String bladeFinish,
            String bladeMaterial,
            String handleConstruction,
            String handleFinish,
            String handleMaterial,
            String averageScore,
            String feelScore,
            String flippingScore,
            String qualityScore,
            String soundScore,
            String durabilityScore,
            List<String> galleryFiles
    ) {
        setCollectionId(collectionId);
        setDisplayName(displayName);
        this.knifeMaker = knifeMaker;
        this.baseKnifeModel = baseKnifeModel;
        setKnifeType(knifeType);
        this.aqquiredDate = aqquiredDate;
        this.creationDate = new Date();
        setIsFavoriteKnife(isFavoriteKnife);
        setIsFavoriteFlipper(isFavoriteFlipper);
        this.coverPhoto = coverPhoto;
        setMsrp(knifeMSRP);
        setOverallLength(overallLength);
        setWeight(weight);
        setPivotSystem(pivotSystem);
        setLatchType(latchType);
        setPinSystem(pinSystem);
        setHasModularBalance(hasModularBalance);
        this.setBalanceValue(balanceValue);
        setBladeStyle(bladeStyle);
        setBladeFinish(bladeFinish);
        setBladeMaterial(bladeMaterial);
        setHandleConstruction(handleConstruction);
        setHandleMaterial(handleMaterial);
        setHandleFinish(handleFinish);
        this.averageScore = averageScore;
        this.feelScore = feelScore;
        this.flippingScore = flippingScore;
        this.qualityScore = qualityScore;
        this.soundScore = soundScore;
        this.durabilityScore = durabilityScore;
        this.galleryFiles = galleryFiles;

        this.hasBeenSold = false;
        this.upForSale = false;
        this.wantToTrade = false;
        this.noLongerHave = false;
    }

    public void setDisplayName(String displayName) {
        if (displayName.isEmpty())
            this.displayName = this.id;
        else
            this.displayName = displayName;
    }

    public void setKnifeType(String knifeType) {
        switch (knifeType.toLowerCase().trim()) {
            case "liveblade":
            case "live_blade":
                this.knifeType = KnifeType.LIVE_BLADE;
                break;
            case "trainer":
                this.knifeType = KnifeType.TRAINER;
                break;
            case "both":
            default:
                this.knifeType = KnifeType.BOTH;
                break;
        }
    }

    public void setIsFavoriteKnife(String isFavoriteKnife) {
        if (!isFavoriteKnife.isEmpty() && isFavoriteKnife.toLowerCase().trim().equals("true"))
            this.isFavoriteKnife = true;
        else
            this.isFavoriteKnife = false;
    }

    public void setIsFavoriteFlipper(String isFavoriteFlipper) {
        if (!isFavoriteFlipper.isEmpty() && isFavoriteFlipper.toLowerCase().trim().equals("true"))
            this.isFavoriteFlipper = true;
        else
            this.isFavoriteFlipper = false;
    }

    public void setMsrp(String msrp) {
        try {
            this.msrp = Double.parseDouble(msrp);
        }
        catch(Exception e) {
            this.msrp = 0.0;
        }
    }

    public void setOverallLength(String overallLength) {
        try {
            this.overallLength = Double.parseDouble(overallLength);
        }
        catch(Exception e) {
            this.overallLength = 0.0;
        }
    }

    public void setWeight(String weight) {
        try {
            this.weight = Double.parseDouble(weight);
        }
        catch(Exception e) {
            this.weight = 0.0;
        }
    }

    public void setPivotSystem(String pivotSystem) {
        switch (pivotSystem.toLowerCase().trim()) {
            case "bushings":
                this.pivotSystem = PivotSystem.BUSHINGS;
                break;
            case "bearings":
                this.pivotSystem = PivotSystem.BEARINGS;
                break;
            case "washers":
                this.pivotSystem = PivotSystem.WASHERS;
                break;
            case "other":
                this.pivotSystem = PivotSystem.OTHER;
                break;
            case "unknown":
            default:
                this.pivotSystem = PivotSystem.UNKNOWN;
                break;
        }
    }

    public void setLatchType(String latchType) {
        switch(latchType.toLowerCase().trim()) {
            case "spring latch":
            case "springlatch":
            case "spring_latch":
            case "spring-latch":
                this.latchType = LatchType.SPRING_LATCH;
                break;
            case "swinglatch":
            case "swing-latch":
            case "swing_latch":
            case "swing latch":
                this.latchType = LatchType.SWING_LATCH;
                break;
            case "latchless":
            case "no latch":
            case "nolatch":
                this.latchType = LatchType.LATCHLESS;
                break;
            case "other":
                this.latchType = LatchType.OTHER;
                break;
            case "unknown":
            default:
                this.latchType = LatchType.UNKNOWN;
                break;
        }
    }

    public void setPinSystem(String pinSystem) {
        switch (pinSystem.toLowerCase().trim()) {
            case "zenpins":
            case "zen-pins":
            case "zen_pins":
                this.pinSystem = PinSystem.ZEN_PINS;
                break;
            case "pinsless":
            case "pinless":
            case "nopins":
            case "no pins":
                this.pinSystem = PinSystem.PINSLESS;
                break;
            case "tangpins":
            case "tang-pins":
            case "tang_pins":
                this.pinSystem = PinSystem.TANG_PINS;
                break;
            case "hiddenzenpins":
            case "hidden-zen-pins":
            case "hidden_zen_pins":
            case "hidden zen pins":
                this.pinSystem = PinSystem.HIDDEN_ZEN_PINS;
                break;
            case "other":
                this.pinSystem = PinSystem.OTHER;
            case "unknown":
            default:
                this.pinSystem = PinSystem.UNKNOWN;
                break;
        }
    }

    public void setHasModularBalance(String hasModularBalance) {
        if (hasModularBalance.toLowerCase().trim().equals("true"))
            this.hasModularBalance = true;
        else
            this.hasModularBalance = false;
    }

    public void setBladeStyle(String bladeStyle) {
        switch (bladeStyle.toLowerCase().trim()) {
            case "tanto":
                this.bladeStyle = BladeStyle.TANTO;
                break;
            case "bowie":
                this.bladeStyle = BladeStyle.BOWIE;
                break;
            case "kukri":
                this.bladeStyle = BladeStyle.KUKRI;
                break;
            case "americantanto":
            case "american-tanto":
            case "american_tanto":
            case "american tanto":
                this.bladeStyle = BladeStyle.AMERICAN_TANTO;
                break;
            case "weehawk":
                this.bladeStyle = BladeStyle.WEEHAWK;
                break;
            case "horseshoe":
            case "horse shoe":
            case "horse-shoe":
            case "horse_shoe":
                this.bladeStyle = BladeStyle.HORSE_SHOE;
                break;
            case "japanesetanto":
            case "japanese tanto":
            case "japanese-tanto":
            case "japanese_tanto":
                this.bladeStyle = BladeStyle.JAPANESE_TANTO;
                break;
            case "spearpoint":
            case "spear point":
            case "spear-point":
            case "spear_point":
                this.bladeStyle = BladeStyle.SPEAR_POINT;
            case "other":
                this.bladeStyle = BladeStyle.OTHER;
            case "unknown":
            default:
                this.bladeStyle = BladeStyle.UNKNOWN;
        }
    }

    public void setBladeFinish(String bladeFinish) {
        switch (bladeFinish.toLowerCase().trim()) {
            case "satin":
                this.bladeFinish = BladeFinish.SATIN;
                break;
            case "stone-wash":
            case "stonewash":
            case "stone wash":
            case "stone_wash":
                this.bladeFinish = BladeFinish.STONE_WASH;
                break;
            case "plain":
                this.bladeFinish = BladeFinish.PLAIN;
                break;
            case "polished":
                this.bladeFinish = BladeFinish.POLISHED;
                break;
            case "mirrorpolished":
            case "mirror polished":
            case "mirror-polished":
            case "mirror_polished":
                this.bladeFinish = BladeFinish.MIRROR_POLISHED;
                break;
            case "dualtone":
            case "dual tone":
            case "dual-tone":
            case "dual_tone":
                this.bladeFinish = BladeFinish.DUALTONE;
                break;
            case "dlc":
                this.bladeFinish = BladeFinish.DLC;
                break;
            case "acidwash":
            case "acid wash":
            case "acid-wash":
            case "acid_wash":
                this.bladeFinish = BladeFinish.ACID_WASH;
                break;
            case "blackwash":
            case "black wash":
            case "black-wash":
            case "black_wash":
                this.bladeFinish = BladeFinish.BLACK_WASH;
                break;
            case "other":
                this.bladeFinish = BladeFinish.OTHER;
                break;
            case "unknown":
            default:
                this.bladeFinish = BladeFinish.UNKNOWN;
                break;
        }
    }

    public void setBladeMaterial(String bladeMaterial) {
        switch (bladeMaterial.toLowerCase().trim()) {
            case "aluminium":
                this.bladeMaterial = BladeMaterial.ALUMINIUM;
                break;
            case "titanium":
                this.bladeMaterial = BladeMaterial.TITANIUM;
                break;
            case "stainlesssteel":
            case "stainless steel":
            case "stainless-steel":
            case "stainless_steel":
                this.bladeMaterial = BladeMaterial.STAINLESS_STEEL;
                break;
            case "plastic":
                this.bladeMaterial = BladeMaterial.PLASTIC;
                break;
            case "6061aluminium":
            case "6061 aluminium":
            case "6061_aluminium":
            case "6061-aluminium":
                this.bladeMaterial = BladeMaterial.ALUMINIUM_6061;
                break;
            case "d2":
                this.bladeMaterial = BladeMaterial.D2;
                break;
            case "s35vn":
                this.bladeMaterial = BladeMaterial.S35VN;
                break;
            case "s32vn":
                this.bladeMaterial = BladeMaterial.S32VN;
                break;
            case "hardened steel":
            case "hardenedsteel":
            case "hardened_steel":
            case "hardened-steel":
                this.bladeMaterial = BladeMaterial.HARDENED_STEEL;
                break;
            case "7075aluminium":
            case "7075 aluminium":
            case "7075_aluminium":
            case "7075-aluminium":
                this.bladeMaterial = BladeMaterial.ALUMINIUM_7075;
                break;
            case "other":
                this.bladeMaterial = BladeMaterial.OTHER;
                break;
            case "unknown":
            default:
                this.bladeMaterial = BladeMaterial.UNKNOWN;
                break;
        }
    }

    public void setHandleConstruction(String handleConstruction) {
        switch (handleConstruction.toLowerCase().trim()) {
            case "channel":
            case "chanel":
                this.handleConstruction = HandleConstruction.CHANNEL;
                break;
            case "sandwhich":
            case "sand which":
                this.handleConstruction = HandleConstruction.SANDWHICH;
                break;
            case "chanwhich":
            case "chan which":
                this.handleConstruction = HandleConstruction.CHANWHICH;
                break;
            case "other":
                this.handleConstruction = HandleConstruction.OTHER;
                break;
            case "unknown":
            default:
                this.handleConstruction = HandleConstruction.UNKNOWN;
                break;
        }
    }

    public void setHandleMaterial(String handleMaterial) {
        switch (handleMaterial.toLowerCase().trim()) {
            case "aluminium":
                this.handleMaterial = HandleMaterial.ALUMINIUM;
                break;
            case "titanium":
                this.handleMaterial = HandleMaterial.TITANIUM;
                break;
            case "g-10":
            case "g10":
            case "g_10":
            case "g 10":
                this.handleMaterial = HandleMaterial.G_10;
                break;
            case "carbonfiber":
            case "carbon fiber":
            case "carbon-fiber":
            case "carbon_fiber":
                this.handleMaterial = HandleMaterial.CARBON_FIBER;
                break;
            case "7075aluminium":
            case "7075 aluminium":
            case "7075_aluminium":
            case "7075-aluminium":
                this.handleMaterial = HandleMaterial.ALUMINIUM_7075;
                break;
            case "6061aluminium":
            case "6061 aluminium":
            case "6061_aluminium":
            case "6061-aluminium":
                this.handleMaterial = HandleMaterial.ALUMINIUM_6061;
                break;
            case "stainlesssteel":
            case "stainless steel":
            case "stainless-steel":
            case "stainless_steel":
                this.handleMaterial = HandleMaterial.STAINLESS_STEEL;
                break;
            case "hardened steel":
            case "hardenedsteel":
            case "hardened_steel":
            case "hardened-steel":
                this.handleMaterial = HandleMaterial.HARDENED_STEEL;
                break;
            case "g-10 titanium":
            case "g/10 titanium":
            case "g10/titanium":
            case "g_10/titanium":
            case "g 10/titanium":
            case "g-10/titanium":
            case "g 10 titanium":
            case "g_10 titanium":
                this.handleMaterial = HandleMaterial.G_10_TITANIUM;
                break;
            case "g-10 aluminium":
            case "g/10 aluminium":
            case "g10/aluminium":
            case "g_10/aluminium":
            case "g 10/aluminium":
            case "g-10/aluminium":
            case "g 10 aluminium":
            case "g_10 aluminium":
            case "other":
                this.handleMaterial = HandleMaterial.G_10_ALUMINIUM;
                break;
            case "plastic":
                this.handleMaterial = HandleMaterial.PLASTIC;
                break;
            case "unknown":
            default:
                this.handleMaterial = HandleMaterial.UNKNOWN;
                break;
        }
    }

    public void setHandleFinish(String handleFinish) {
        switch (handleFinish.toLowerCase().trim()) {
            case "satin":
                this.handleFinish = HandleFinish.SATIN;
                break;
            case "plain":
                this.handleFinish = HandleFinish.PLAIN;
                break;
            case "stonewash":
            case "stone wash":
            case "stone-wash":
            case "stone_wash":
                this.handleFinish = HandleFinish.STONE_WASH;
                break;
            case "polished":
                this.handleFinish = HandleFinish.POLISHED;
                break;
            case "mirror polished":
            case "mirrorpolished":
            case "mirror-polished":
            case "mirror_polished":
                this.handleFinish = HandleFinish.MIRROR_POLISHED;
                break;
            case "acidwash":
            case "acid wash":
            case "acid-wash":
            case "acid_wash":
                this.handleFinish = HandleFinish.ACID_WASHED;
                break;
            case "anodized":
                this.handleFinish = HandleFinish.ANODIZED;
                break;
            case "zirblasted":
            case "zir-blasted":
            case "zir blasted":
            case "zir_blasted":
                this.handleFinish = HandleFinish.ZIR_BLASTED;
                break;
            case "blackwash":
            case "black wash":
            case "black-wash":
            case "black_wash":
                this.handleFinish = HandleFinish.BLACK_WASH;
                break;
            case "beadblasted":
            case "bead blasted":
            case "bead-blasted":
            case "bead_blasted":
                this.handleFinish = HandleFinish.BEAD_BLASTED;
                break;
            case "other":
                this.handleFinish = HandleFinish.OTHER;
                break;
            case "unknown":
            default:
                this.handleFinish = HandleFinish.UNKNOWN;
                break;
        }
    }
}
