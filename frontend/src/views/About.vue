<template>
  <div id="main-about-container">
    <div id="creators-title">
      <h1>The Creators</h1>
    </div>
    <div id="bios">
      <div class="creator-bio">
        <img src="@/assets/MJ.jpg" alt="mj-img" />
        <div class="bio-text">
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
        <div class="bio-text">
            <h4>Julianna Tilson</h4>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione,
            non. Cumque, veniam. Mollitia, cum facere, temporibus dignissimos
            quisquam magnam nulla architecto dolores repellat dolor voluptatem
            vitae et consequuntur voluptas non!
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/CC.jpg" alt="cc-img" />
        <div class="bio-text">
            <h4>Christopher Carter</h4>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione,
            non. Cumque, veniam. Mollitia, cum facere, temporibus dignissimos
            quisquam magnam nulla architecto dolores repellat dolor voluptatem
            vitae et consequuntur voluptas non!
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/NES.jpg" alt="nick-img" />
        <div class="bio-text">
            <h4>Nicholas Simmons</h4>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione,
            non. Cumque, veniam. Mollitia, cum facere, temporibus dignissimos
            quisquam magnam nulla architecto dolores repellat dolor voluptatem
            vitae et consequuntur voluptas non!
          </p>
        </div>
      </div>

      <div class="creator-bio">
        <img src="@/assets/RPB.jpg" alt="rpb-img" />
        <div class="bio-text">
            <h4>Ryan Beech</h4>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione,
            non. Cumque, veniam. Mollitia, cum facere, temporibus dignissimos
            quisquam magnam nulla architecto dolores repellat dolor voluptatem
            vitae et consequuntur voluptas non!
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

.bio-text {
    padding: 5px;
    display: flex;
    flex-direction: column;
   justify-content: center;
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