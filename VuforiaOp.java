package org.firstinspires.ftc.robotcontroller.internal.SteelMagnolias;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

public class VuforiaOp extends LinearOpMode {
    private boolean targetVisible = false;
    @Override
public void runOpMode() {
    VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
    params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
    params.vuforiaLicenseKey = "AXcJnVv/////AAABmY6RSgPMpkU/gTq/ohM7Dp1GRcTjE+uMBhojoC7ZTX6arz9hkiASgNsqnXAtnVHQ/oEqIBLtfRGbX2xTSYe97drbnYkx2RIoDjiYevKtY96gj17+N5sifH2trQuu+/BOicels1rO1WySfkAiVCChif8XO9PaHCwj7WwlEIY1q2crPQvaokLDDHUr/gIroIy+J8cf33a62WaYAofaGmSn9lS20oHPteQ9EDrDDIyFdIBRmp7aLE/7c6HHEwb00m489jZ1Xkj8P3ixFbbpXnXTujjo2vNpoG1w4bzmNFJ2lyMdwJERuUQzNxbrVxsXj6TU4DCoSSNMo87Kgfyf1sC12ODf5N4qVby7346R3bjtpCoW";
    params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
    boolean inView = false;
    telemetry.addData("inVIew", inView);

    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);
    Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 5);

    VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("Skystone");
    beacons.get(0).setName("TargetElement");

    List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
    allTrackables.addAll(beacons);

    waitForStart();

    beacons.activate();

    //While the code is running it will check to see if any of the trackables are visable
    while (opModeIsActive()) {
        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                telemetry.update();
                targetVisible = true;
            }
        }
    }


    /*while (opModeIsActive()){
        for(VuforiaTrackable beac : beacons) {
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

            boolean inView = pose != null;
            telemetry.addData("inVIew", inView);

            if (inView) {
                VectorF translation = pose.getTranslation();

                telemetry.addData(beac.getName() + "Translation", translation);

                double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));

                telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);
            }
        }
        telemetry.update();
    }*/
}
}
