package mchorse.bbs_mod.ui.utils;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class DrawContext
{
    private MatrixStack stack;

    public MatrixStack getMatrices()
    {
        return this.stack;
    }

    public void setMatrices(MatrixStack matrices)
    {
        this.stack = matrices;
    }

    public void enableScissor(int x1, int y1, int x2, int y2)
    {
        DrawableHelper.enableScissor(x1, y1, x2, y2);
    }

    public void disableScissor()
    {
        DrawableHelper.disableScissor();
    }

    public void draw()
    {}
}