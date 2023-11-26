package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.compat.alexcave.ConchFake;
import com.moonstone.moonstonemod.compat.alexcave.IrradiatedOrbFake;
import com.moonstone.moonstonemod.compat.alexcave.conch;
import com.moonstone.moonstonemod.compat.alexcave.irradiatedorb;
import com.moonstone.moonstonemod.compat.cataclysm.create;
import com.moonstone.moonstonemod.compat.cataclysm.hurtring;
import com.moonstone.moonstonemod.compat.cataclysm.timegold;
import com.moonstone.moonstonemod.compat.enigmaticlegacy.blessingseye;
import com.moonstone.moonstonemod.compat.enigmaticlegacy.plague;
import com.moonstone.moonstonemod.compat.enigmaticlegacy.soulapple;
import com.moonstone.moonstonemod.compat.enigmaticlegacy.soulelytra;
import com.moonstone.moonstonemod.compat.toughasnails.TemperatureEye;
import com.moonstone.moonstonemod.compat.toughasnails.TemperatureEyeFake;
import com.moonstone.moonstonemod.compat.twilightforest.base.GammaEye;
import com.moonstone.moonstonemod.compat.twilightforest.base.TreeOrb;
import com.moonstone.moonstonemod.compat.twilightforest.dna.Iondeficit;
import com.moonstone.moonstonemod.compat.twilightforest.dna.Ionsurge;
import com.moonstone.moonstonemod.compat.twilightforest.dna.Reshuffle;
import com.moonstone.moonstonemod.compat.twilightforest.dna.Sensitivity;
import com.moonstone.moonstonemod.compat.twilightforest.lich.BlackRune;
import com.moonstone.moonstonemod.compat.twilightforest.lich.LichHead;
import com.moonstone.moonstonemod.compat.twilightforest.lich.LifeRune;
import com.moonstone.moonstonemod.compat.twilightforest.lich.SnowRune;
import com.moonstone.moonstonemod.compat.twilightforest.naga.NagaBlock;
import com.moonstone.moonstonemod.compat.twilightforest.naga.NagaOrb;
import com.moonstone.moonstonemod.compat.twilightforest.naga.NagaRing;
import com.moonstone.moonstonemod.compat.twilightforest.naga.NagaStone;
import com.moonstone.moonstonemod.content.Text;
import com.moonstone.moonstonemod.content.book.Soulbook;
import com.moonstone.moonstonemod.content.charm.MaxAmout;
import com.moonstone.moonstonemod.content.charm.WarCharm;
import com.moonstone.moonstonemod.content.item.DNADish;
import com.moonstone.moonstonemod.content.item.Doom.doomfruit;
import com.moonstone.moonstonemod.content.item.Doom.doomstone;
import com.moonstone.moonstonemod.content.item.E.*;
import com.moonstone.moonstonemod.content.item.G.deerbadge;
import com.moonstone.moonstonemod.content.item.G.goldbook;
import com.moonstone.moonstonemod.content.item.G.undead;
import com.moonstone.moonstonemod.content.item.I.industrybox;
import com.moonstone.moonstonemod.content.item.I.industryeye;
import com.moonstone.moonstonemod.content.item.MAX.*;
import com.moonstone.moonstonemod.content.item.MoonStoneItem.decayedstone;
import com.moonstone.moonstonemod.content.item.N.*;
import com.moonstone.moonstonemod.content.item.P.*;
import com.moonstone.moonstonemod.content.item.R.*;
import com.moonstone.moonstonemod.content.item.S.*;
import com.moonstone.moonstonemod.content.item.Stone.ectoplasmslate;
import com.moonstone.moonstonemod.content.item.Stone.twstone;
import com.moonstone.moonstonemod.content.item.T.*;
import com.moonstone.moonstonemod.content.item.Teleport;
import com.moonstone.moonstonemod.content.item.amout.Event;
import com.moonstone.moonstonemod.content.item.amout.ectoplasmstone;
import com.moonstone.moonstonemod.content.item.amout.goldstone;
import com.moonstone.moonstonemod.content.item.amout.twistedstone;
import com.moonstone.moonstonemod.content.item.laji.*;
import com.moonstone.moonstonemod.content.item.mcmod.curseevent;
import com.moonstone.moonstonemod.content.item.mcmod.sevencurse;
import com.moonstone.moonstonemod.content.item.mcmod.sevenorb;
import com.moonstone.moonstonemod.content.item.medicine.DNARunes.airdnarune;
import com.moonstone.moonstonemod.content.item.medicine.DNARunes.waterdnarune;
import com.moonstone.moonstonemod.content.item.medicine.TheNecora.*;
import com.moonstone.moonstonemod.content.item.medicine.extend.apple;
import com.moonstone.moonstonemod.content.item.medicine.extend.medicinebox;
import com.moonstone.moonstonemod.content.item.medicine.extend.necora;
import com.moonstone.moonstonemod.content.item.medicine.med.*;
import com.moonstone.moonstonemod.content.item.moris.*;
import com.moonstone.moonstonemod.event.SoulLoot;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Init {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoonstoneMod.MODID);
	public static final RegistryObject<Item> soulbook = REGISTRY.register("soulbook", () -> new Soulbook());
	public static final RegistryObject<Item> mblock = REGISTRY.register("mblock", () -> new MorisBlock());
	public static final RegistryObject<Item> mshell = REGISTRY.register("mshell", () -> new MorisShell());
	public static final RegistryObject<Item> mbox = REGISTRY.register("mbox", () -> new MorisBox());
	public static final RegistryObject<Item> mbottle = REGISTRY.register("mbottle", () -> new MorisBottle());
	public static final RegistryObject<Item> morb = REGISTRY.register("morb", () -> new MorisOrb());
	public static final RegistryObject<Item> mring = REGISTRY.register("mring", () -> new MorisRing());


	public static final RegistryObject<Item> soul = REGISTRY.register("soul", () -> new soul());
	public static final RegistryObject<Item> soulball = REGISTRY.register("soulball", () -> new soulball());
	public static final RegistryObject<Item> soulcloub = REGISTRY.register("soulcloub", () -> new soulcloub());
	public static final RegistryObject<Item> soulcube = REGISTRY.register("soulcube", () -> new soulcube());

	public static final RegistryObject<Item> ectoplasmcube = REGISTRY.register("ectoplasmcube", () -> new ectoplasmcube());
	public static final RegistryObject<Item> ectoplasmslate = REGISTRY.register("ectoplasmslate", () -> new ectoplasmslate());
	public static final RegistryObject<Item> ectoplasmball = REGISTRY.register("ectoplasmball", () -> new ectoplasmball());
	public static final RegistryObject<Item> ectoplasmcloub = REGISTRY.register("ectoplasmcloub", () -> new ectoplasmcloub());


	public static final RegistryObject<Item> woodenhorseshoe = REGISTRY.register("woodenhorseshoe", () -> new woodenhorseshoe());
	public static final RegistryObject<Item> ironhorseshoe = REGISTRY.register("ironhorseshoe", () -> new ironhorseshoe());
	public static final RegistryObject<Item> goldhorseshoe = REGISTRY.register("goldhorseshoe", () -> new goldhorseshoe());

	public static final RegistryObject<Item> diamondhorseshoe = REGISTRY.register("diamondhorseshoe", () -> new diamondhorseshoe());
	public static final RegistryObject<Item> ectoplasmhorseshoe = REGISTRY.register("ectoplasmhorseshoe", () -> new ectoplasmhorseshoe());
	public static final RegistryObject<Item> superhorseshoe = REGISTRY.register("superhorseshoe", () -> new superhorseshoe());
	public static final RegistryObject<Item> badgeofthedead = REGISTRY.register("badgeofthedead", () -> new badgeofthedead());
	public static final RegistryObject<Item> ectoplasmbadge = REGISTRY.register("ectoplasmbadge", () -> new ectoplasmbadge());
	public static final RegistryObject<Item> ectoplasmapple = REGISTRY.register("ectoplasmapple", () -> new ectoplasmapple());

	public static final RegistryObject<Item> ectoplasmstone = REGISTRY.register("ectoplasmstone", () -> new ectoplasmstone());
	public static final RegistryObject<Item> twistedstone = REGISTRY.register("twistedstone", () -> new twistedstone());
	public static final RegistryObject<Item> Event = REGISTRY.register("eventeee", () -> new Event());
	public static final RegistryObject<Item> twistedring = REGISTRY.register("twistedring", () -> new twistedring());
	public static final RegistryObject<Item> twistedbadge = REGISTRY.register("twistedbadge", () -> new twistedbadge());
	public static final RegistryObject<Item> twistedrock = REGISTRY.register("twistedrock", () -> new twistedrock());
	public static final RegistryObject<Item> twistedyoke = REGISTRY.register("twistedyoke", () -> new twistedyoke());
	public static final RegistryObject<Item> SOULLOOT = REGISTRY.register("soullootevent", () -> new SoulLoot());
	public static final RegistryObject<Item> greedcrystal = REGISTRY.register("greedcrystal", () -> new greedcrystal());
	public static final RegistryObject<Item> warcrystal = REGISTRY.register("warcrystal", () -> new warcrystal());
	public static final RegistryObject<Item> biggreedcrystal = REGISTRY.register("biggreedcrystal", () -> new biggreedcrystal());
	public static final RegistryObject<Item> bigwarcrystal = REGISTRY.register("bigwarcrystal", () -> new bigwarcrystal());
	public static final RegistryObject<Item> ectoplasmprism = REGISTRY.register("ectoplasmprism", () -> new ectoplasmprism());
	public static final RegistryObject<Item> fortunecrystal = REGISTRY.register("fortunecrystal", () -> new fortunecrystal());
	public static final RegistryObject<Item> mayhemcrystal = REGISTRY.register("mayhemcrystal", () -> new mayhemcrystal());
	public static final RegistryObject<Item> slatecube = REGISTRY.register("slatecube", () -> new slatecube());
	public static final RegistryObject<Item> slate = REGISTRY.register("slate", () -> new slate());
	public static final RegistryObject<Item> bigslate = REGISTRY.register("bigslate", () -> new bigslate());
	public static final RegistryObject<Item> goldstone = REGISTRY.register("goldstone", () -> new goldstone());
	public static final RegistryObject<Item> undead = REGISTRY.register("undead", () -> new undead());
	public static final RegistryObject<Item> goldbook = REGISTRY.register("goldbook", () -> new goldbook());
	public static final RegistryObject<Item> deerbadge = REGISTRY.register("deerbadge", () -> new deerbadge());
	public static final RegistryObject<Item> redamout = REGISTRY.register("redamout", () -> new redamout());
	public static final RegistryObject<Item> blueamout = REGISTRY.register("blueamout", () -> new blueamout());
	public static final RegistryObject<Item> greedamout = REGISTRY.register("greedamout", () -> new greedamout());
	public static final RegistryObject<Item> decayedstone = REGISTRY.register("decayedstone", () -> new decayedstone());
	public static final RegistryObject<Item> treasure = REGISTRY.register("treasure", () -> new treasure());
	public static final RegistryObject<Item> nanorobot = REGISTRY.register("nanorobot", () -> new nanorobot());
	public static final RegistryObject<Item> nanocube = REGISTRY.register("nanocube", () -> new nanocube());
	public static final RegistryObject<Item> twstone = REGISTRY.register("twstone", () -> new twstone());
	public static final RegistryObject<Item> Text = REGISTRY.register("sadasdas", () -> new Text());
	public static final RegistryObject<Item> twistedhand = REGISTRY.register("twistedhand", () -> new twistedhand());
	public static final RegistryObject<Item> soulapple = REGISTRY.register("soulapple", () -> new soulapple());


	public static final RegistryObject<Item> blessingseye = REGISTRY.register("blessingseye", () -> new blessingseye());
	public static final RegistryObject<Item> soulelytra = REGISTRY.register("soulelytra", () -> new soulelytra());
	public static final RegistryObject<Item> mkidney = REGISTRY.register("mkidney", () -> new MorisKidney());
	public static final RegistryObject<Item> battery = REGISTRY.register("battery", () -> new battery());
	public static final RegistryObject<Item> meye = REGISTRY.register("meye", () -> new MorisEye());
	public static final RegistryObject<Item> soulbattery = REGISTRY.register("soulbattery", () -> new soulbattery());
	public static final RegistryObject<Item> ectoplasmbattery = REGISTRY.register("ectoplasmbattery", () -> new ectoplasmbattery());
	public static final RegistryObject<Item> mbattery = REGISTRY.register("mbattery", () -> new MorisBattery());
	public static final RegistryObject<Item> plague = REGISTRY.register("plague", () -> new plague());

	public static final RegistryObject<Item> whiteorb = REGISTRY.register("whiteorb", () -> new whiteorb());
	public static final RegistryObject<Item> blackeorb = REGISTRY.register("blackeorb", () -> new blackeorb());

	public static final RegistryObject<Item> blackhead = REGISTRY.register("blackhead", () -> new blackhead());
	public static final RegistryObject<Item> warcharm = REGISTRY.register("warcharm", () -> new WarCharm());
	public static final RegistryObject<Item> maxamout = REGISTRY.register("maxamout", () -> new MaxAmout());
    /*
    public static final RegistryObject<Item> firecharm = REGISTRY.register("firecharm", () -> new firecharm());
    public static final RegistryObject<Item> mobcharm = REGISTRY.register("mobcharm", () -> new mobcharm());
    public static final RegistryObject<Item> soulcharm = REGISTRY.register("soulcharm", () -> new soulcharm());
    public static final RegistryObject<Item> watercharm = REGISTRY.register("watercharm", () -> new watercharm());


     */

	/*
	public static final RegistryObject<Item> dermalcalcification = REGISTRY.register("dermalcalcification", () -> new dermalcalcification());
	public static final RegistryObject<Item> cranialelephantitis = REGISTRY.register("cranialelephantitis", () -> new cranialelephantitis());
	public static final RegistryObject<Item> bottle = REGISTRY.register("bottle", () -> new bottle());
	public static final RegistryObject<Item> batcytopathic = REGISTRY.register("batcytopathic", () -> new batcytopathic());
	public static final RegistryObject<Item> TAG = REGISTRY.register("tag_tag", () -> new TAG());
	public static final RegistryObject<Item> batbinding = REGISTRY.register("batbinding", () -> new batbinding());
	public static final RegistryObject<Item> batvampiric = REGISTRY.register("batvampiric", () -> new batvampiric());


	 */
	public static final RegistryObject<Item> sevencurse = REGISTRY.register("sevencurse", () -> new sevencurse());
	public static final RegistryObject<Item> hurtring = REGISTRY.register("hurtring", () -> new hurtring());
	public static final RegistryObject<Item> create = REGISTRY.register("create", () -> new create());
	public static final RegistryObject<Item> hellbattery = REGISTRY.register("hellbattery", () -> new hellbattery());
	public static final RegistryObject<Item> evilcandle = REGISTRY.register("evilcandle", () -> new evilcandle());
	public static final RegistryObject<Item> evilmug = REGISTRY.register("evilmug", () -> new evilmug());
	public static final RegistryObject<Item> obsidianring = REGISTRY.register("obsidianring", () -> new obsidianring());
	public static final RegistryObject<Item> diemug = REGISTRY.register("diemug", () -> new diemug());
	public static final RegistryObject<Item> medicinebox = REGISTRY.register("medicinebox", () -> new medicinebox());
	public static final RegistryObject<Item> polyphagia = REGISTRY.register("polyphagia", () -> new polyphagia());
	public static final RegistryObject<Item> quadriceps = REGISTRY.register("quadriceps", () -> new quadriceps());
	public static final RegistryObject<Item> nagablock = REGISTRY.register("nagablock", () -> new NagaBlock());
	public static final RegistryObject<Item> nagastone = REGISTRY.register("nagastone", () -> new NagaStone());
	public static final RegistryObject<Item> nagaring = REGISTRY.register("nagaring", () -> new NagaRing());
	public static final RegistryObject<Item> nagaorb = REGISTRY.register("nagaorb", () -> new NagaOrb());
	public static final RegistryObject<Item> lichhead = REGISTRY.register("lichhead", () -> new LichHead());
	public static final RegistryObject<Item> liferune = REGISTRY.register("liferune", () -> new LifeRune());
	public static final RegistryObject<Item> snowrune = REGISTRY.register("snowrune", () -> new SnowRune());
	public static final RegistryObject<Item> masticatory = REGISTRY.register("masticatory", () -> new masticatory());
	public static final RegistryObject<Item> calcification = REGISTRY.register("calcification", () -> new calcification());
	public static final RegistryObject<Item> reanimation = REGISTRY.register("reanimation", () -> new reanimation());
	public static final RegistryObject<Item> necora = REGISTRY.register("necora", () -> new necora());
	public static final RegistryObject<Item> atpoverdose = REGISTRY.register("atpoverdose", () -> new atpoverdose());
	public static final RegistryObject<Item> autolytic = REGISTRY.register("autolytic", () -> new autolytic());
	public static final RegistryObject<Item> putrefactive = REGISTRY.register("putrefactive", () -> new putrefactive());
	public static final RegistryObject<Item> apple = REGISTRY.register("apple", () -> new apple());
	public static final RegistryObject<Item> ambush = REGISTRY.register("ambush", () -> new ambush());
	public static final RegistryObject<Item> fermentation = REGISTRY.register("fermentation", () -> new fermentation());
	public static final RegistryObject<Item> regenerative = REGISTRY.register("regenerative", () -> new regenerative());
	public static final RegistryObject<Item> sevenorb = REGISTRY.register("sevenorb", () -> new sevenorb());
	public static final RegistryObject<Item> reshuffle = REGISTRY.register("reshuffle", () -> new Reshuffle());
	public static final RegistryObject<Item> sensitivity = REGISTRY.register("sensitivity", () -> new Sensitivity());
	public static final RegistryObject<Item> iondeficit = REGISTRY.register("iondeficit", () -> new Iondeficit());
	public static final RegistryObject<Item> ionsurge = REGISTRY.register("ionsurge", () -> new Ionsurge());
	public static final RegistryObject<Item> treeorb = REGISTRY.register("treeorb", () -> {
		if (ModList.get().isLoaded("twilightforest")) {
			return new TreeOrb();
		} else {
			return new Item(new Item.Properties().stacksTo(1).rarity(Rarity.create("asdsa", ChatFormatting.GOLD)));
		}
	});
	public static final RegistryObject<Item> timegold = REGISTRY.register("timegold", () -> new timegold());
	public static final RegistryObject<Item> nightmareeye = REGISTRY.register("nightmareeye", () -> new nightmareeye());
	public static final RegistryObject<Item> nightmareorb = REGISTRY.register("nightmareorb", () -> new nightmareorb());
	public static final RegistryObject<Item> nightmarestone = REGISTRY.register("nightmarestone", () -> new nightmarestone());
	public static final RegistryObject<Item> nightmarewater = REGISTRY.register("nightmarewater", () -> new nightmarewater());
	public static final RegistryObject<Item> nightmaremoai = REGISTRY.register("nightmaremoai", () -> new nightmaremoai());
	public static final RegistryObject<Item> nightmarecharm = REGISTRY.register("nightmarecharm", () -> new nightmarecharm());
	public static final RegistryObject<Item> nightmaretreasure = REGISTRY.register("nightmaretreasure", () -> new nightmaretreasure());
	public static final RegistryObject<Item> nightmareanchor = REGISTRY.register("nightmareanchor", () -> new nightmareanchor());
	public static final RegistryObject<Item> waterdnarune = REGISTRY.register("waterdnarune", () -> new waterdnarune());

	public static final RegistryObject<Item> airdnarune = REGISTRY.register("airdnarune", () -> new airdnarune());
	public static final RegistryObject<Item> rageeye = REGISTRY.register("rageeye", () -> new rageeye());
	public static final RegistryObject<Item> rageapple = REGISTRY.register("rageapple", () -> new rageapple());
	public static final RegistryObject<Item> ragepear = REGISTRY.register("ragepear", () -> new ragepear());
	public static final RegistryObject<Item> ragecharm = REGISTRY.register("ragecharm", () -> new ragecharm());
	public static final RegistryObject<Item> DNADish = REGISTRY.register("dnadish", () -> new DNADish());
	public static final RegistryObject<Item> doomstone = REGISTRY.register("doomstone", () -> new doomstone());
	public static final RegistryObject<Item> doomfruit = REGISTRY.register("doomfruit", () -> new doomfruit());
	public static final RegistryObject<Item> tp = REGISTRY.register("tp", () -> new Teleport());
	public static final RegistryObject<Item> conch = REGISTRY.register("conch", () -> {
		if (ModList.get().isLoaded("alexscaves")) {
			return new conch();
		} else {
			return new ConchFake();
		}
	});
	public static final RegistryObject<Item> irradiatedorb = REGISTRY.register("irradiatedorb", () -> {
		if (ModList.get().isLoaded("alexscaves")) {
			return new irradiatedorb();
		} else {
			return new IrradiatedOrbFake();
		}
	});
	public static final RegistryObject<Item> curseevent = REGISTRY.register("curseevent", () -> new curseevent());
	public static final RegistryObject<Item> blackrune = REGISTRY.register("blackrune", () -> new BlackRune());
	public static final RegistryObject<Item> gammaeye = REGISTRY.register("gammaeye", () -> new GammaEye());
	public static final RegistryObject<Item> ragebox = REGISTRY.register("ragebox", () -> new ragebox());
	public static final RegistryObject<Item> industrybox = REGISTRY.register("industrybox", () -> new industrybox());
	public static final RegistryObject<Item> industryeye = REGISTRY.register("industryeye", () -> new industryeye());

	public static final RegistryObject<Item> temperatureeye = REGISTRY.register("temperatureeye", () -> {
		if (ModList.get().isLoaded("toughasnails")) {
			return new TemperatureEye();
		} else {
			return new TemperatureEyeFake();
		}
	});

	public static final RegistryObject<Item> endhead = REGISTRY.register("endhead", () -> new endhead());

}
