# Chapter 12 - Project 4
# Swarm 4.0
# Nicolas Guerrero
# 5/16/13

import math
from livewire import games, color
import random

games.init(screen_width = 640, screen_height = 480, fps = 50)


class Collider(games.Sprite):
    """ Sprite that collides with others. """
    def turret_missile(self):
        """ Check for collisions. """

        if self.overlapping_sprites:
            for sprite in self.overlapping_sprites:
                Turret.kills += 1
                sprite.die()
            self.die()
        
    def nuke(self):
        if self.overlapping_sprites:
            for sprite in self.overlapping_sprites:
                sprite.die()
                self.ship.score.value += 30
            #no self.die() so it goes through all sprites
        
    def update(self):
        """ Check for collisions. """
        if self.overlapping_sprites:
            for sprite in self.overlapping_sprites:
                sprite.die()
            self.die()               

    def die(self):
        """ Destroy self and leave explosion behind. """
        an_explosion = Explosion(x = self.x, y = self.y)
        games.screen.add(an_explosion)
        self.destroy()


class Ship(Collider):
    """ A moving ship. """
    image = games.load_image("ship.bmp")
    MISSILE_DELAY = games.screen.fps
    NUKE_DELAY = games.screen.fps
    move_speed = 5
    
    def __init__(self, game):
        """ Initialize ship sprite. """
        games.Sprite.__init__(self,
                              image = Ship.image,
                              x = games.screen.width/2,
                              bottom = games.screen.height - 5)
        self.game = game
        self.missile_wait = 0
        
        self.score = games.Text(value = 0,
                                size = 30,
                                color = color.white,
                                x = 585, y = 20,
                                is_collideable = False)
        games.screen.add(self.score)

        if self.score.value >= 0 and self.score.value < 30:
            upgrade_message = games.Message(value = "Center Ship[c]",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = 3 * games.screen.fps,
                                            is_collideable = False)                                
            games.screen.add(upgrade_message)
    
    def update(self):
        """ Move ship based on keys pressed. """
        Collider.update(self)

        # move left or right
        if games.keyboard.is_pressed(games.K_LEFT):
            self.x -= int(Ship.move_speed)          
        if games.keyboard.is_pressed(games.K_RIGHT):
            self.x += int(Ship.move_speed)

        # Lets ship wrap around screen
        if self.left > games.screen.width:
            self.right = 0
            
        if self.right < 0:
            self.left = games.screen.width

        # Centers the ship in the middle of the screen
        if games.keyboard.is_pressed(games.K_c):
            self.x = 320

        self.check_fire()
        self.check_score()

    def check_score(self):
        """Checks score and displays things accordingly"""
        #check if score is high enough to shoot tri-bolt
        if self.score.value >= 5000 and self.score.value <= 5500:
            upgrade_message = games.Message(value = "Unlocked Tri-Bolt[f]",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = 3 * games.screen.fps,
                                            is_collideable = False)                                
            games.screen.add(upgrade_message)
        #check if score is high enough to shoot nuke
        if self.score.value >= 10000 and self.score.value <= 11000:
            upgrade_message = games.Message(value = "Unlocked Nuke[d]",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = 3 * games.screen.fps,
                                            is_collideable = False)                                
            games.screen.add(upgrade_message)
        #Checks for score and changes missile delay accordingly
        if self.score.value > 2500 and self.score.value < 3000:
            Ship.MISSILE_DELAY = games.screen.fps/15
            upgrade_message = games.Message(value = "Rapid Fire(15x)",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
        elif self.score.value > 750 and self.score.value < 1000:
            Ship.MISSILE_DELAY = games.screen.fps/5
            upgrade_message = games.Message(value = "Rapid Fire(5x)",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
        elif self.score.value > 250 and self.score.value < 500:
            Ship.MISSILE_DELAY = games.screen.fps/2
            upgrade_message = games.Message(value = "Rapid Fire(2x)",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
        #Checks for score and changes ship speed accordingly
        if self.score.value > 1000 and self.score.value < 1250:
            Ship.move_speed = 20
            upgrade_message = games.Message(value = "Sonic Speed(4x)",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
        elif self.score.value > 500 and self.score.value < 750:
            Ship.move_speed = 10
            upgrade_message = games.Message(value = "Super Speed(2x)",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
        #Checks to see if missile can speed up
        if self.score.value > 1250 and self.score.value < 1500:
            Missile.SPEED = -10
            upgrade_message = games.Message(value = "Missile Speed(3.33x)",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
        #Checks to see if can deploy turret
        if self.score.value > 2000 and self.score.value < 2500:
            upgrade_message = games.Message(value = "Unlocked Turret(2x)[s]",
                                            size = 30,
                                            color = color.green,
                                            x = games.screen.width/2,
                                            y = games.screen.width/10 + 20,
                                            lifetime = games.screen.fps/5,
                                            is_collideable = False)
            games.screen.add(upgrade_message)

    def check_fire(self):
        """ Check if ship fires new missile. """
        if self.missile_wait > 0:
            self.missile_wait -= 1

        #Regular shooting   
        if games.keyboard.is_pressed(games.K_SPACE) and self.missile_wait == 0:
            new_missile = Missile(self)
            games.screen.add(new_missile)
            self.missile_wait = Ship.MISSILE_DELAY

        #Nuke
        if games.keyboard.is_pressed(games.K_d) and self.missile_wait == 0 and self.score.value >= 10000:
            new_nuke = Nuke(self)
            games.screen.add(new_nuke)
            self.missile_wait = Ship.NUKE_DELAY

        #Tri-Bolt
        if games.keyboard.is_pressed(games.K_f) and self.missile_wait == 0 and self.score.value >= 2500:
            new_missile = Missile_rtilt(self)
            games.screen.add(new_missile)
            self.missile_wait = Ship.MISSILE_DELAY

            new_missile = Missile_ltilt(self)
            games.screen.add(new_missile)
            self.missile_wait = Ship.MISSILE_DELAY

            new_missile = Missile(self)
            games.screen.add(new_missile)
            self.missile_wait = Ship.MISSILE_DELAY

        #Turret
        if games.keyboard.is_pressed(games.K_s) and self.missile_wait == 0 and Turret.total < 2 and self.score.value >= 2000:
            new_turret = Turret(self)
            games.screen.add(new_turret)
            self.missile_wait = Ship.NUKE_DELAY
            

    def die(self):
        self.game.end()
        Collider.die(self)


class Missile(Collider):
    """ A missile launched by the player's ship. """
    image = games.load_image("missile.bmp")
    sound = games.load_sound("missile.wav")
    SPEED = -3

    def __init__(self, ship):
        """ Initialize missile sprite. """
        Missile.sound.play()
        
        games.Sprite.__init__(self, image = Missile.image,
                              x = ship.x, bottom = ship.top - 1,
                              dy = Missile.SPEED)
        self.ship = ship

    def update(self):
        """ Move the missile. """
        Collider.update(self)
        
        if self.top < 0:
            self.destroy()

    def die(self):
        """ Increase ship score and then call base class die method. """
        self.ship.score.value += 30
        Collider.die(self)

class Nuke(Collider):
    """ A Laser launched by the player's ship to clear the screen of enemies. """
    image = games.load_image("Nuke.bmp")
    sound = games.load_sound("missile.wav")
    SPEED = -30

    def __init__(self, ship):
        """ Initialize missile sprite. """
        Nuke.sound.play()
        
        games.Sprite.__init__(self, image = Nuke.image,
                              x = ship.x, bottom = ship.top - 1,
                              dy = Nuke.SPEED)
        self.ship = ship

    def update(self):
        """ Move the missile. """
        Collider.nuke(self)
        
        if self.top < 0:
            self.destroy()

    def die(self):
        """ Increase ship score and then call base class die method. """
        self.ship.score.value += 30
        Collider.die(self)

class Missile_ltilt(Collider):
    """ A missile launched by the player's ship to the left diagonal. """
    image = games.load_image("missile_ltilt.bmp")
    sound = games.load_sound("missile.wav")
    SPEED = -10

    def __init__(self, ship):
        """ Initialize missile sprite. """
        Missile.sound.play()
        
        games.Sprite.__init__(self, image = Missile.image,
                              x = ship.x - 20, bottom = ship.top - 1,
                              dx = Missile.SPEED, dy = Missile.SPEED)
        self.ship = ship

    def update(self):
        """ Move the missile. """
        Collider.update(self)
        
        if self.top < 0:
            self.destroy()

    def die(self):
        """ Increase ship score and then call base class die method. """
        self.ship.score.value += 30
        Collider.die(self)

class Missile_rtilt(Collider):
    """ A missile launched by the player's ship to the right diagonal. """
    image = games.load_image("missile_rtilt.bmp")
    sound = games.load_sound("missile.wav")
    SPEED = 10

    def __init__(self, ship):
        """ Initialize missile sprite. """
        Missile.sound.play()
        
        games.Sprite.__init__(self, image = Missile.image,
                              x = ship.x + 20, bottom = ship.top - 1,
                              dx = Missile_rtilt.SPEED,
                              dy = Missile.SPEED)
        self.ship = ship

    def update(self):
        """ Move the missile. """
        Collider.update(self)
        
        if self.top < 0:
            self.destroy()

    def die(self):
        """ Increase ship score and then call base class die method. """
        self.ship.score.value += 30
        Collider.die(self)

class Turret(Collider):
    """ A missile launched by the player's ship. """
    image = games.load_image("turret.bmp")
    sound = games.load_sound("missile.wav")
    MISSILE_DELAY = games.screen.fps/2
    ROTATION_STEP = 8
    turn = "right"
    total = 0
    kills = 0
    
    def __init__(self, ship):
        """ Initialize missile sprite. """
        Missile.sound.play()

        Turret.total += 1
        self.missile_wait = 0
        
        games.Sprite.__init__(self, image = Turret.image,
                              x = ship.x, y = 400)
        self.ship = ship

        

    def update(self):
        """ Move the missile. """
        Collider.update(self)

        if Turret.turn == "right":
            if self.angle < 360 and self.angle > 270:
                self.angle += Turret.ROTATION_STEP
            if self.angle >= 0:
                self.angle += Turret.ROTATION_STEP
            if self.angle == 80:
                Turret.turn = "left"
        if Turret.turn == "left":
            if self.angle < 90:
                self.angle -=Turret.ROTATION_STEP
            if self.angle < 360 and self.angle >= 280:
                self.angle -=Turret.ROTATION_STEP
            if self.angle > 270 and self.angle < 280:
                Turret.turn = "right"

        if Turret.kills > 19 and Turret.kills < 22:
            upgrade_message = games.Message(value = "Turret Speed(2x)",
                                            size = 30,
                                            color = color.blue,
                                            x = self.x ,
                                            bottom = self.top + 5,
                                            lifetime = games.screen.fps/10,
                                            is_collideable = False)
            games.screen.add(upgrade_message)
            Turret.MISSILE_DELAY = games.screen.fps/4
        self.fire()
        
    def fire(self):
        if self.missile_wait > 0:
            self.missile_wait -= 1
            
        if self.missile_wait == 0:
            new_missile = Missile_turret(self.x, self.y, self.angle)
            games.screen.add(new_missile)
            self.missile_wait = Turret.MISSILE_DELAY

    def die(self):
        """ Increase ship score and then call base class die method. """
        Turret.total -= 1
        Collider.die(self)


class Missile_turret(Collider):
    """ A missile launched by the player's ship. """
    image = games.load_image("missile_single.bmp")
    sound = games.load_sound("missile.wav")
    SPEED = -10
    BUFFER = 40
    VELOCITY_FACTOR = 7

    def __init__(self, ship_x, ship_y, ship_angle):
        """ Initialize missile sprite. """
        Missile.sound.play()
        
        # convert to radians
        angle = ship_angle * math.pi / 180  

        # calculate missile's starting position 
        buffer_x = Missile_turret.BUFFER * math.sin(angle)
        buffer_y = Missile_turret.BUFFER * -math.cos(angle)
        x = ship_x + buffer_x 
        y = ship_y + buffer_y 

        # calculate missile's velocity components
        dx = Missile_turret.VELOCITY_FACTOR * math.sin(angle)
        dy = Missile_turret.VELOCITY_FACTOR * -math.cos(angle)
            
        games.Sprite.__init__(self, image = Missile_turret.image,
                                  x = x,y = y,
                                  dx = dx, dy = dy)

    def update(self):
        """ Move the missile. """
        Collider.turret_missile(self)

        
        
        if self.top < 0:
            self.destroy()

class Fly(games.Animation):
    """ A an enemy fly. """
    images = ["fly1.bmp",
              "fly2.bmp"]
    total = 0

    def __init__(self, game):
        """ Initialize fly animation. """  
        games.Animation.__init__(self,
                          images = Fly.images,
                          x = random.randrange(games.screen.width),
                          y = random.randrange(-100, -25),
                          dx = random.randrange(-3, 3),
                          dy = random.randrange(10, 15)/10.0,
                          repeat_interval = 10, n_repeats = 0)
        # attribute for Game object
        self.game = game
        # increment total
        Fly.total += 1

    def update(self):
        """ Move the fly. """
        if self.left > games.screen.width:
            self.right = 0
            
        if self.right < 0:
            self.left = games.screen.width

        if self.top > games.screen.height:
            self.top = random.randrange(-100, -25)

    # destroy, update total
    def die(self):
        """ Destroy fly. """
        # update total and advance to next wave if no more flies
        Fly.total -= 1
        if Fly.total == 0:
            self.game.advance()
        self.destroy()

class Explosion(games.Animation):
    """ Explosion animation. """
    sound = games.load_sound("explosion.wav")
    images = ["explosion1.bmp",
              "explosion2.bmp",
              "explosion3.bmp",
              "explosion4.bmp",
              "explosion5.bmp",
              "explosion6.bmp",
              "explosion7.bmp",
              "explosion8.bmp",
              "explosion9.bmp"]

    def __init__(self, x, y):
        games.Animation.__init__(self, images = Explosion.images,
                                 x = x, y = y,
                                 repeat_interval = 4, n_repeats = 1,
                                 is_collideable = False)
        Explosion.sound.play()


class Game(object):
    """ The game itself. """
    # intialize Game Object
    def __init__(self):
        """ Initialize Game object. """
        self.level = 0
        self.sound_advance = games.load_sound("level.wav")

    def play(self):
        """ A round of the game. """
        stars_image = games.load_image("background.jpg", transparent = False)
        games.screen.background = stars_image

        games.music.load("theme.mid")
        games.music.play(-1)

        the_ship = Ship(game = self)
        games.screen.add(the_ship)

        self.advance()

        games.screen.mainloop()

    # Advance to next level
    def advance(self):
        """ Advance to the next game level. """
        self.level += 1
        
        for i in range(3*self.level):
            new_fly = Fly(game = self)
            games.screen.add(new_fly)

        level_message = games.Message(value = "Wave " + str(self.level),
                                      size = 40,
                                      color = color.yellow,
                                      x = games.screen.width/2,
                                      y = games.screen.width/10,
                                      lifetime = 3 * games.screen.fps,
                                      is_collideable = False)
        games.screen.add(level_message)

        if self.level > 1:
            self.sound_advance.play()

    def end(self):
        """ End the game. """       
        end_message = games.Message(value = "Game Over",
                                    size = 90,
                                    color = color.red,
                                    x = games.screen.width/2,
                                    y = games.screen.height/2,
                                    lifetime = 5 * games.screen.fps,
                                    after_death = games.screen.quit,
                                    is_collideable = False)
        games.screen.add(end_message)
            

def main():
    swarm = Game()
    swarm.play()

main()
