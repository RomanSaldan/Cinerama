package com.lynx.cinerama.data.model.movies.reviews;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieReview implements Parcelable {
    /**
     * id: "54b0210d925141746c000154",
     author: "Vincent",
     content: "**Voracious Bull** Every time I try to enjoy a Martin Scorcese movie post-1980 I ask myself what made _Raging Bull_ so great? Did Marty burn out after his Jake LaMotta bio, the same way Coppola did after _Apocalypse Now_? These were risky movies, driven by passion, bordering on madness. Did Marty and Francis lose their natural passion for making films because industry priorities dismissed 70's-style hyper-realism for sensational summer blockbusters and CGI spectacles? And why is Scorcese considered one of the greatest director when he really only made one truly superb movie? When I first saw _Raging Bull_ in 1980 I immediately knew I was watching something that transcended the typical cinematic experience. Genius was shimmering out of every perforated frame flickering on the screen. Paul, Marty, Bobby, Mike and Thelma created something that was completely absorbing. The style and substance was perfectly fueled by a flawless emotional narrative. Every element was orchestrated just right. The audience was spellbound. We were watching greatness. A rare and unique organic creation. I'm still waiting for Robert Redford to correct a travesty of justice and hand his Ocscar over to it's rightful owner. And I'm still waiting for Scorcese to match his own brilliance. But that's like expecting Tarantino to top _Pulp Fiction_. There's a better chance Orson Welles rises up from the dead to one up _Citizen Kane_. After _Raging Bull_, Scorcese has made a string of pictures ranging from not bad to pretty good. All well-made, thoughtful and meticulously crafted films, but nothing special; certainly nothing profound. Contrary to popular opinion, _Goodfellas_ is not a great movie. I was not swept away by the saga. I was annoyed rather by the fragmented non-stop soundtrack and incessant up-tempo style. Marty wasn't risking anything anymore. He seemed to be afraid of boring the audience. Perhaps he was trying to revive the 1940's never-let-up screwball-bouncing farcical Preston Sturges and co. comedies. But this is a mafia film. This should have been right up Marty's alley. It's been almost a quarter of a century since De Niro got his face busted in a boxing ring. Since the raw, robust and naive will-power of LaMotta's youth plunged into the pathetic, brutal, bone-headed stupidity of his later years. Similarly, Scorsese hasn't registered a knock-out punch since. Would his ensuing movies be considered great if someone else directed them? Would I have liked them all better if Raging Bull had never been made? _The Wolf of Wall Street_ is another exhausting affair. It tries too hard to excite and entertain us. It's afraid of slowing down, allowing us to ponder or examine the complexities of excessive greed, shameless wealth and unbridled capitalism. It desperately wants to arouse us. Like a neglected clown at a child's birthday trying to be loved and taken seriously while draining the life out of the party. Give Leo and Jonah an "A" for effort. They couldn't have tried harder if they broke out into song and dance every ten minutes. And they convincingly appeared to enjoy themselves freely indulging in coke, ludes and naked women every five minutes. It was nice of Leo to step back while Margot Robbie took her routine and obligatory, supporting-actress hissy fit. And you know a filmmaker has a lot of faith and confidence in his work when a superfluous narration track is added, to plug those terrifying noiseless gaps. Whatever happened to poignant, suspenseful, sure-footed, gripping, emotionally-arresting dramas that take you on a nervy, wild ride to a thoroughly gratifying climax? A truly great director from Kurosowa to, well, Scorcese-(circa 1979) would have plotted the rise and fall of a maniacal protagonist along deeper and more affecting lines even at the risk boring its audience for one minute.",
     url: "https://www.themoviedb.org/review/54b0210d925141746c000154"
     */
    public String id;
    public String author;
    public String content;
    public String url;

    protected MovieReview(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieReview> CREATOR = new Parcelable.Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };
}