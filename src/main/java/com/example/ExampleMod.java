package com.example.pearlcatch;
                List<EnderPearlEntity> pearls =
                        serverWorld.getEntitiesByClass(
                                EnderPearlEntity.class,
                                searchBox,
                                entity -> true
                        );

                for (EnderPearlEntity pearl : pearls) {

                    Entity owner = windCharge.getOwner();

                    if (!(owner instanceof ServerPlayerEntity catcher)) {
                        continue;
                    }

                    Vec3d pearlPos = pearl.getPos();
                    Vec3d pearlVelocity = pearl.getVelocity();

                    catcher.teleport(
                            serverWorld,
                            pearlPos.x,
                            pearlPos.y,
                            pearlPos.z,
                            java.util.Set.of(),
                            catcher.getYaw(),
                            catcher.getPitch(),
                            true
                    );

                    catcher.setVelocity(
                            pearlVelocity.multiply(MOMENTUM_MULTIPLIER)
                    );

                    catcher.velocityModified = true;

                    serverWorld.spawnParticles(
                            ParticleTypes.CLOUD,
                            pearlPos.x,
                            pearlPos.y,
                            pearlPos.z,
                            20,
                            0.2,
                            0.2,
                            0.2,
                            0.02
                    );

                    serverWorld.playSound(
                            null,
                            pearlPos.x,
                            pearlPos.y,
                            pearlPos.z,
                            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                            SoundCategory.PLAYERS,
                            1.0f,
                            1.2f
                    );

                    pearl.discard();
                    windCharge.discard();

                    break;
                }
            }
        });
    }
}
