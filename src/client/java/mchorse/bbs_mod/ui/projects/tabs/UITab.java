package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.ui.framework.UIContext;
import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.Area;
import mchorse.bbs_mod.ui.utils.ScrollDirection;
import mchorse.bbs_mod.ui.utils.icons.Icons;
import mchorse.bbs_mod.utils.colors.Colors;

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
        area.w = area.h = 20;
        area.x += this.area.w - 20;

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
            float dx = context.mouseX - this.lastX;
            float dy = context.mouseY - this.lastY;
            float abs = (float) Math.sqrt(dx * dx + dy * dy);

            if (abs > 10)
            {
                this.dragging = false;

                this.tabs.split(this, dx > dy ? ScrollDirection.HORIZONTAL : ScrollDirection.VERTICAL);
            }
        }

        context.batcher.clip(this.area, context);
        this.area.render(context.batcher, this.hashCode() & Colors.RGB | Colors.A50);
        context.batcher.icon(Icons.DRAG_CORNER, this.area.ex() - 16, this.area.y);

        super.render(context);

        context.batcher.unclip(context);
    }
}