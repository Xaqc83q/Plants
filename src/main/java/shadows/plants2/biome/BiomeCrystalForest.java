package shadows.plants2.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.gen.WorldGenCrystals;
import shadows.plants2.init.ModRegistry;

public class BiomeCrystalForest extends AbstractBiomeBase {

	static final WorldGenerator CRYSTALS = new WorldGenCrystals();

	public BiomeCrystalForest() {
		super("crystal_forest", new BiomeProperties("Crystal Forest"));
		topBlock = ModRegistry.GROUNDCOVER.getDefaultState();
		this.decorator.treesPerChunk = 10;
		this.decorator.flowersPerChunk = 0;
		this.decorator.grassPerChunk = 15;
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.flowers.clear();
		this.flowers.add(new FlowerEntry(ModRegistry.CRYSTAL.getStateFor(Crystals.CRYSTAL_SHARD), 80));
		this.flowers.add(new FlowerEntry(ModRegistry.CRYSTAL.getStateFor(Crystals.DARK_CRYSTAL_SHARD), 20));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return rand.nextFloat() >= 0.8F ? ModRegistry.DARK_CRYSTAL_TREE : ModRegistry.CRYSTAL_TREE;
	}

	@Override
	public void plantFlower(World world, Random rand, BlockPos pos) {
		FlowerEntry flower = WeightedRandom.getRandomItem(rand, flowers);
		if (flower != null) world.setBlockState(pos, flower.state, 3);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return CRYSTALS;
	}

	@Override
	protected void register() {
		//super.register();
	}

}
