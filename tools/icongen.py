"""
Compose the Modrinth icon for The Pacifist.

Usage:
    python tools/icongen.py

Reads textures from vanilla_textures/ (see VANILLA_DIR below).
The mod itself adds zero new items, so the icon is composed
entirely from vanilla item textures.

Writes:
    icon.png (256x256, RGBA)
    src/main/resources/assets/pacifist/icon.png (used by fabric.mod.json)
"""
from PIL import Image, ImageDraw
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
VANILLA_DIR = Path(__file__).resolve().parent / "vanilla_textures"
OUT = ROOT / "icon.png"
OUT_MOD = ROOT / "src/main/resources/assets/pacifist/icon.png"

SIZE = 256
TILE = 64
GAP = 16
BG = (44, 32, 64)     # deep peaceful violet (think nighttime/dreamy)
FRAME = (22, 16, 36)  # darker violet border
BORDER_RADIUS = 24

# Center = brush (the signature non-violent tool). Surrounding cells = the drops it unlocks.
GRID = [
    "gunpowder.png",        "blaze_powder.png",    "ghast_tear.png",
    "shulker_shell.png",    "brush.png",           "phantom_membrane.png",
    "spider_eye.png",       "slime_ball.png",      "string.png",
]


def load_texture(name: str) -> Image.Image:
    img = Image.open(VANILLA_DIR / name).convert("RGBA")
    return img.resize((TILE, TILE), Image.NEAREST)


def make_icon() -> Image.Image:
    icon = Image.new("RGBA", (SIZE, SIZE), (0, 0, 0, 0))
    draw = ImageDraw.Draw(icon)

    draw.rounded_rectangle((0, 0, SIZE - 1, SIZE - 1), radius=BORDER_RADIUS, fill=FRAME)
    pad = 8
    draw.rounded_rectangle(
        (pad, pad, SIZE - 1 - pad, SIZE - 1 - pad),
        radius=BORDER_RADIUS - 6,
        fill=BG,
    )

    for i, name in enumerate(GRID):
        col, row = i % 3, i // 3
        x = GAP + col * (TILE + GAP)
        y = GAP + row * (TILE + GAP)
        try:
            tex = load_texture(name)
            icon.paste(tex, (x, y), tex)
        except FileNotFoundError as e:
            print(f"!! Missing texture: {e.filename}")

    return icon


if __name__ == "__main__":
    img = make_icon()
    img.save(OUT)
    OUT_MOD.parent.mkdir(parents=True, exist_ok=True)
    img.save(OUT_MOD)
    print(f"Wrote {OUT} and {OUT_MOD}")
