package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.ui.framework.UIContext;
import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.Area;
import mchorse.bbs_mod.ui.utils.ScrollDirection;
import mchorse.bbs_mod.ui.utils.icons.Icons;

public class UITab extends UIElement
{
    public final UITabs tabs;

    private boolean dragging;
    private int lastX;
    private int lastY;

    public UITab(UITabs tabs)
    {
        this.tabs = tabs;
    }

    @Override
    protected boolean subMouseClicked(UIContext context)
    {
        Area area = new Area();

        area.copy(this.area);
        area.w = area.h = 16;
        area.x += this.area.w - 16;
        area.y += this.area.h - 16;

        if (area.isInside(context))
        {
            this.dragging = true;
            this.lastX = context.mouseX;
            this.lastY = context.mouseY;

            return true;
        }

        return super.subMouseClicked(context);
    }

    @Override
    protected boolean subMouseReleased(UIContext context)
    {
        this.dragging = false;

        return super.subMouseReleased(context);
    }

    @Override
    public void render(UIContext context)
    {
        if (this.dragging)
        {
            float dx = Math.abs(context.mouseX - this.lastX);
            float dy = Math.abs(context.mouseY - this.lastY);
            float abs = (float) Math.sqrt(dx * dx + dy * dy);

            if (abs > 10)
            {
                this.dragging = false;

                context.render.postRunnable(() ->
                {
                    this.tabs.split(this, context.mouseX, context.mouseY, dx > dy ? ScrollDirection.HORIZONTAL : ScrollDirection.VERTICAL);
                });
            }
        }

        context.batcher.clip(this.area, context);
        this.renderBackground(context);
        context.batcher.icon(Icons.DRAG_CORNER, this.area.ex() - 16, this.area.ey() - 16);

        super.render(context);

        context.batcher.unclip(context);
    }

    protected void renderBackground(UIContext context)
    {}
}