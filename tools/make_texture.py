# Generates the 16x16 brass texture: python3 tools/make_texture.py
from PIL import Image
import os
OUT = 'src/main/resources/assets/compactspeedctrl/textures/block/compact_speed_controller.png'
img = Image.new('RGBA', (16, 16))
px = img.load()
EDGE=(68,46,20,255); HI=(240,206,134,255); MID=(196,150,74,255); LOW=(150,110,50,255)
BOLT=(232,196,126,255); BOLT_D=(90,64,28,255); RING=(96,70,34,255)
RECESS=(34,26,18,255); AMBER=(255,176,46,255); AMBER_D=(204,124,22,255)
for y in range(16):
    for x in range(16):
        t = y/15
        base = HI if t < 0.15 else (MID if t < 0.7 else LOW)
        if x in (0,15) or y in (0,15): base = EDGE
        elif x == 1 or y == 1: base = HI
        elif x == 14 or y == 14: base = LOW
        px[x,y] = base
for (x,y) in [(3,3),(12,3),(3,12),(12,12)]:
    px[x,y]=BOLT; px[x+1,y]=BOLT_D; px[x,y+1]=BOLT_D
cx, cy, r = 7.5, 7.5, 4.2
for y in range(16):
    for x in range(16):
        d = ((x-cx)**2 + (y-cy)**2) ** 0.5
        if d <= r: px[x,y] = RECESS
        elif d <= r + 0.9 and 2 < x < 13 and 2 < y < 13: px[x,y] = RING
for (x,y) in [(7,4),(8,4),(7,5),(8,5)]: px[x,y] = AMBER
for (x,y) in [(7,6),(8,6),(7,10),(8,10)]: px[x,y] = AMBER_D
os.makedirs(os.path.dirname(OUT), exist_ok=True)
img.save(OUT)
print('texture written to', OUT)
