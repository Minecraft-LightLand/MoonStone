package com.moonstone.moonstonemod.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class time extends TextureSheetParticle {
    public time(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ, int minlife, boolean checkSkylight) {
        super(level, x, y, z, 0.0, 0.0, 0.0);
        this.lifetime = 200;


        this.setParticleSpeed(0,0,0);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public void tick() {

        this.roll += 0.02f;
        this.oRoll += 0.02f;
        this.quadSize = 0.8f + 0.05f;
        this.alpha -= 0.035f;
        if (alpha<=0){
            this.remove();
        }
        super.tick();

    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            time particle = new time(level, x, y, z, 0, 0, 0, 0, true);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}
