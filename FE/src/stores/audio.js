import { defineStore } from "pinia";

export const useAudioStore = defineStore("audioData", {
  state: () => ({
    audioData: false,
  }),
  actions: {
    // toggleAudio() {
    //     this.audioData = !this.audioData;
    // }
  },
});
