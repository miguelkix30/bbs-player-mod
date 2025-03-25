package mchorse.bbs_mod.projects;

import mchorse.bbs_mod.settings.values.ValueGroup;
import mchorse.bbs_mod.settings.values.ValueInt;
import mchorse.bbs_mod.settings.values.ValueLong;

public class Project extends ValueGroup
{
    public final ValueInt width = new ValueInt("width", 1280, 2, 8192);
    public final ValueInt height = new ValueInt("height", 720, 2, 8192);

    public final ValueInt motionBlur = new ValueInt("motion_blur", 0, 0, 10);
    public final ValueInt heldFrames = new ValueInt("held_frames", 1, 1, 30);
    public final ValueInt frameRate = new ValueInt("frame_rate", 60, 1, 360);

    public final ValueInt start = new ValueInt("start", 0, 0, 1000000);
    public final ValueInt end = new ValueInt("end", 240, 0, 1000000);

    public final ValueInt x = new ValueInt("x", 0, -30_000_00, 30_000_000);
    public final ValueInt y = new ValueInt("y", 0, -30_000_00, 30_000_000);
    public final ValueInt z = new ValueInt("z", 0, -30_000_00, 30_000_000);

    public final KeyframeRepo repo = new KeyframeRepo("repo");

    public Project()
    {
        super("");

        this.add(this.width);
        this.add(this.height);

        this.add(this.motionBlur);
        this.add(this.heldFrames);
        this.add(this.frameRate);

        this.add(this.x);
        this.add(this.y);
        this.add(this.z);

        this.add(this.repo);
    }
}