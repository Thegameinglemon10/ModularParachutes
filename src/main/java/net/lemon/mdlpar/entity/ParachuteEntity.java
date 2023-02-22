package net.lemon.mdlpar.entity;

import net.minecraft.entity.Entity;

public class ParachuteEntity extends Entity {
    private static final DataParameter<Integer> PARACHUTE_COLOR = EntityDataManager.createKey(ParachuteEntity.class, DataSerializers.VARINT);

    private static double deltaRotation;
    private static double curLavaDistance;
    private static boolean rideInWater;

    private boolean leftKeyDown;
    private boolean rightKeyDown;
    private boolean forwardKeyDown;
    private boolean backKeyDown;
    private int turnProgress;

    public static boolean leftTurn;
    public static boolean rightTurn;
    public static double forwardMotion;

    private double chuteX;
    private double chuteY;
    private double chuteZ;
    private double chuteYaw;
    private double chutePitch;

    @OnlyIn(Dist.CLIENT)
    private double velocityX;
    @OnlyIn(Dist.CLIENT)
    private double velocityY;
    @OnlyIn(Dist.CLIENT)
    private double velocityZ;

    private void controlParachute() {
        if (isBeingRidden()) {
            double motionFactor = 0.0f;

            if (forwardKeyDown) {
                motionFactor += Consts.FORWARD_MOMENTUM;
            }

            if (backKeyDown) {
                motionFactor -= Consts.BACK_MOMENTUM;
            }

            if (leftKeyDown) {
                deltaRotation -= Consts.ROTATION_MOMENTUM;
            }
            if (rightKeyDown) {
                deltaRotation += Consts.ROTATION_MOMENTUM;
            }

            // slight forward momentum while turning
            if (rightKeyDown != leftKeyDown && !forwardKeyDown && !backKeyDown) {
                motionFactor += Consts.SLIDE_MOMENTUM;
            }

            leftTurn = leftKeyDown;
            rightTurn = rightKeyDown;
            forwardMotion = motionFactor;

            rotationYaw += deltaRotation;

            double motionY = currentDescentRate();
            double motionX = MathHelper.sin((float) Math.toRadians(-rotationYaw)) * motionFactor;
            double motionZ = MathHelper.cos((float) Math.toRadians(rotationYaw)) * motionFactor;
            setMotion(getMotion().add(motionX, motionY, motionZ));

            if (isBadWeather() && rand.nextBoolean()) {
                applyTurbulence(world.isThundering());
            }
        }
    }

    @Override
    public void tick() {
        Entity skyDiver = getControllingPassenger();
        // the player has pressed LSHIFT or been killed,
        // may be necessary for LSHIFT to kill the parachute
        if (skyDiver == null && !world.isRemote) { // server side
            remove();
            return;
        }

        generateContrails();

        prevPosX = getPosV().x;
        prevPosY = getPosV().y;
        prevPosZ = getPosV().z;

        super.tick();
        setPacketCoordinates(getPosV().x, getPosV().y, getPosV().z);
        if (canPassengerSteer()) {
            updateMotion();

            setPosition(getPosV().x, getPosV().y, getPosV().z);
            // apply momentum/decay
            Vec3d curMotion = getMotion();
            setMotion(curMotion.x * Consts.DECAY_MOMENTUM, curMotion.y * (curMotion.y < 0.0 ? 0.96 : 0.98), curMotion.z * Consts.DECAY_MOMENTUM);
            deltaRotation *= 0.9;

            if (world.isRemote) {
                controlParachute();
            }
            // move the PARACHUTE with the motion equations applied
            move(MoverType.SELF, getMotion());
        }

        // something bad happened, somehow the skydiver was killed.
        if (!world.isRemote && skyDiver != null && !skyDiver.isAlive()) { // server side
            remove();
        }

        doBlockCollisions();
    }

    public void updateMotion() {
        if (canPassengerSteer()) {
            turnProgress = 0;
            setPacketCoordinates(getPosV().x, getPosV().y, getPosV().z);
        }
        if (turnProgress > 0) {
            double dx = getPosV().x + (chuteX - getPosV().x) / (double) turnProgress;
            double dy = getPosV().y + (chuteY - getPosV().y) / (double) turnProgress;
            double dz = getPosV().z + (chuteZ - getPosV().z) / (double) turnProgress;
            double delta_r = MathHelper.wrapDegrees(chuteYaw - (double) rotationYaw);
            rotationYaw = (float) ((double) rotationYaw + delta_r / (double) turnProgress);
            rotationPitch = (float) ((double) rotationPitch + (chutePitch - (double) rotationPitch) / (double) turnProgress);
            --turnProgress;
            setPosition(dx, dy, dz);
            setRotation(rotationYaw, rotationPitch);
        }
    }

    // determines the descent rate based on whether or not
    // the space bar has been pressed. weather and lava affect
    // the final result.
    private double currentDescentRate(/*Entity skydiver*/) {
		double descentRate;

		descentRate = calcHeatSourceThermals();

		descentRate += (world.isRaining() ? 0.002 : world.isThundering() ? 0.004 : 0.0);

		if (getPosV().y >= Consts.MAX_ALTITUDE) {
		descentRate = Consts.DRIFT;
		}

		return -descentRate;
		}


protected void applyYawToEntity(Entity entityToUpdate) {
		entityToUpdate.setRenderYawOffset(rotationYaw);
		float yaw = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - rotationYaw);
		float yawClamp = MathHelper.clamp(yaw, -Consts.HEAD_TURN_ANGLE, Consts.HEAD_TURN_ANGLE);
		entityToUpdate.prevRotationYaw += yawClamp - yaw;
		entityToUpdate.rotationYaw += yawClamp - yaw;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
		}


@OnlyIn(Dist.CLIENT)
@Override
public void setVelocity(double x, double y, double z) {
		velocityX = x;
		velocityY = y;
		velocityZ = z;
		setMotion(velocityX, velocityY, velocityZ);
		}

@OnlyIn(Dist.CLIENT)
@Override
public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
		chuteX = x;
		chuteY = y;
		chuteZ = z;
		chuteYaw = yaw;
		chutePitch = pitch;
		turnProgress = 10;
		setMotion(velocityX, velocityY, velocityZ);
		}

@

		SuppressWarnings("unused")
public enum Color {
	BLACK("black"),
	BLUE("blue"),
	BROWN("brown"),
	CYAN("cyan"),
	GRAY("gray"),
	GREEN("green"),
	LIGHT_BLUE("light_blue"),
	LIGHT_GRAY("light_gray"),
	LIME("lime"),
	MAGENTA("magenta"),
	ORANGE("orange"),
	PINK("pink"),
	PURPLE("purple"),
	RED("red"),
	WHITE("white"),
	YELLOW("yellow"),
	CAMO("camo"),
	RAINBOW("rainbow");

	private final String name;

	Color(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public static ParachuteEntity.Color byID(int id) {
		ParachuteEntity.Color[] chuteColors = values();
		if (id < 0 || id >= chuteColors.length) {
			id = 0;
		}
		return chuteColors[id];
	}

	public static ParachuteEntity.Color getColorFromString(String colorStr) {
		ParachuteEntity.Color[] chuteColors = values();

		for (Color color : chuteColors) {
			if (color.getName().equals(colorStr)) {
				return color;
			}
		}
		return chuteColors[0];
	}
}

}
