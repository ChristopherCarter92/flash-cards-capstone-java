<template>
  <div id="main-about-container">
    <div id="creators-title">
      <h1>The Creators</h1>
    </div>
    <div id="bios">
      <div class="creator-bio">
        <img src="@/assets/MJ.jpg" alt="mj-img" />
        <div class="bio-text-left">
          <h4>Megan Janes</h4>
          <p>
            Born and raised in Cleveland, Ohio - Megan Janes is an
            ever-resilient Browns fan with a love for the Great Lakes and winter
            snowstorms. New to the tech world, her previous career had her
            teaching and consulting as a science teacher and ed-tech aficionado.
            After years of interest and self-learning about programming, Megan
            took a leap and joined the Apprenti Cincinnati organization, seeking
            an apprenticeship with a local tech business. She will spend her
            next year at P&G working to hone software development skills she
            acquired through a 14 week Tech Elevator bootcamp.
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/JT.jpg" alt="jt-img" />
        <div class="bio-text-right">
          <h4>Julianna Tilson</h4>
          <p>
            Hi! My name is Julianna, I'm a Java student at Tech Elevator with a graduation date of December 2020.
            Until Graduation?
            -I'm learning the fundamentals of Java in a developer bootcamp, honing my skills so that upon graduation I will be fully equipped and prepared to join a development team.
            My Aspirations?
            - I plan to continue my professional and personal development so I can bring the most up to date professional skills to my future team.
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/CC.jpg" alt="cc-img" />
        <div class="bio-text-left">
          <h4>Christopher Carter</h4>
          <p>
            Christopher Carter is Cincinnati born and raised. He studied music
            performance at Northwestern University in Evanston Illinois, dabbled
            in audio technologies, and followed his curiosity to Tech Elevator
            where he has spent the last 14 weeks studying software development.
            He enjoys performing, painting, cooking, web design, weight-lifting
            and hiking. Currently located in Louisville Kentucky, he is
            passionate about all things creative and expressive, and is actively
            looking for work as a new developer.
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/NES.jpg" alt="nick-img" />
        <div class="bio-text-right">
          <h4>Nicholas Simmons</h4>
          <p>
           Nicholas has spent the last 15 years as an investigator for a law firm before deciding to become a software developer after he lost a dare with a hypnotist. He is 6’0” tall and used to get regular haircuts before the pandemic. Nick is looking forward to his new career as a software developer and to continue to learn new technologies as the field continually evolves.
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/RPB.jpg" alt="rpb-img" />
        <div class="bio-text-left">
          <h4>Ryan Beech</h4>
          <p>
            Do you have a Minnesota tattoo? Ryan Beech doesn't either, but his
            pride for his home state is at that level. His previous career as an
            ultrasound technician had him harnessing the power of sonar to keep
            people healthy and happy. Years of interest in technology at work
            and at home as a PC gamer culminated in Ryan taking a huge risk:
            picking up his life, moving to Ohio, and joining a coding bootcamp
            in Cincinnati. Ryan is now a junior developer, putting his
            programming skills to work on many side projects, including this
            one!
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

img {
  width: 245px;
}

#creators-title {
  display: flex;
  justify-content: center;
}

#main-about-container {
  background-image: linear-gradient(#569fad, white);
}

#bios {
  display: flex;
  flex-direction: column;
}

.bio-text-left {
  padding: 5px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.bio-text-right {
  padding: 5px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
  text-align: end;
}

.creator-bio {
  display: flex;
  padding: 5px;
  margin-bottom: 5px;
}

.creator-bio:nth-child(2) img {
  order: 2;
}

.creator-bio:nth-child(4) img {
  order: 2;
}
</style>