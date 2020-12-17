<template>
  <div id="main-about-container">
    <div id="creators-title">
      <h1>The Creators</h1>
    </div>
    <div id="bios">
      <div id="megan-janes-bio">
        <h4>Megan Janes</h4>
        <img src="@/assets/MJ.jpg" alt="mj-img" />
        <p>Born and raised in Cleveland, Ohio - Megan Janes is an ever-resilient Browns fan with a love for the Great Lakes and winter snowstorms. New to the tech world, her previous career had her teaching and consulting as a science teacher and ed-tech aficionado. After years of interest and self-learning about programming, Megan took a leap and joined the Apprenti Cincinnati organization, seeking an apprenticeship with a local tech business. She will spend her next year at  P&G working to hone software development skills she acquired through a 14 week Tech Elevator bootcamp.</p>
      </div>
      <div id="julianna-tilson-bio">
        <h4>Julianna Tilson</h4>
        <img src="@/assets/JT.jpg" alt="jt-img" />
        <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dolorum facilis aut, quos obcaecati delectus dolor? In obcaecati aut quisquam suscipit, alias cupiditate rerum? Modi sunt atque quasi iure sint veniam?</p>
      </div>
      <div id="christopher-carter-bio">
        <h4>Christopher Carter</h4>
        <img src="@/assets/CC.jpg" alt="cc-img" />
        <p>Christopher Carter is Cincinnati born and raised. He studied music performance at Northwestern University in Evanston Illinois, dabbled in audio technologies, and followed his curiosity to Tech Elevator where he has spent the last 14 weeks studying software development. He enjoys performing, painting, cooking, web design, weight-lifting and hiking. Currently located in Louisville Kentucky, he is passionate about all things creative and expressive,  and is actively looking for work as a new developer.</p>
      </div>
      <div id="nicholas-simmons-bio">
        <h4>Nicholas Simmons</h4>
        <img src="@/assets/NES.jpg" alt="nick-img" />
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Assumenda pariatur quo provident aut a, eius voluptatibus nemo natus alias atque perferendis aliquam consectetur vitae nesciunt sapiente, doloremque porro officiis excepturi!</p>
      </div>
      <div id="ryan-beech-bio">
        <h4>Ryan Beech</h4>
        <img src="@/assets/RPB.jpg" alt="rpb-img" />
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione, non. Cumque, veniam. Mollitia, cum facere, temporibus dignissimos quisquam magnam nulla architecto dolores repellat dolor voluptatem vitae et consequuntur voluptas non!</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>

img { 
    height: 245px;
}

#creators-title {
    display: flex;
    justify-content: center;
}

#main-about-container {
  background-image: linear-gradient(#569fad, white);
  height: 100vh;
}

#bios {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
}
</style>