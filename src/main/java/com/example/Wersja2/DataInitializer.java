package com.example.Wersja2;

import com.example.Wersja2.module.MyPost;
import com.example.Wersja2.module.MyPostTag;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyUserRepository;
import com.example.Wersja2.service.MyPostService;
import com.example.Wersja2.service.MyTagService;
import com.example.Wersja2.service.MyUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MyUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyPostService postService;
    private final MyTagService tagService;

    private final MyUserService userService;

    public DataInitializer(MyUserRepository userRepository, PasswordEncoder passwordEncoder, MyPostService postService, MyTagService tagService, MyUserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postService = postService;
        this.tagService = tagService;

        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        MyUser user = new MyUser();
        user.setUsername("asia12");
        user.setEmail("asia12@o2.pl");
        user.setPassword("Asia12");
        userService.registerUser(user);

        MyUser user2 = new MyUser();
        user2.setUsername("barbaraGotuje");
        user2.setEmail("barbara@wp.pl");
        user2.setPassword("Basia1");
        userService.registerUser(user2);

        MyUser user3 = new MyUser();
        user3.setUsername("cezary99");
        user3.setEmail("cezarek21@wp.pl");
        user3.setPassword("Cezary1");
        userService.registerUser(user3);

        MyUser user4 = new MyUser();
        user4.setUsername("daniel12");
        user4.setEmail("daniel87@gmail.com");
        user4.setPassword("Daniel1");
        userService.registerUser(user4);

        MyUser user5 = new MyUser();
        user5.setUsername("ewa22");
        user5.setEmail("ewa22@example.com");
        user5.setPassword("Ewa2022");
        userService.registerUser(user5);

        MyUser user6 = new MyUser();
        user6.setUsername("filip11");
        user6.setEmail("filip11@example.com");
        user6.setPassword("Filip1");
        userService.registerUser(user6);

        MyUser user7 = new MyUser();
        user7.setUsername("grazyna56");
        user7.setEmail("grazyna56@example.com");
        user7.setPassword("Gra1yna");
        userService.registerUser(user7);

        MyUser user8 = new MyUser();
        user8.setUsername("henryk432");
        user8.setEmail("henryk432@example.com");
        user8.setPassword("Hen8ryk");
        userService.registerUser(user8);

        MyUser user9 = new MyUser();
        user9.setUsername("iza33");
        user9.setEmail("iza33@example.com");
        user9.setPassword("Izab3la");
        userService.registerUser(user9);

        MyUser user10 = new MyUser();
        user10.setUsername("janek44");
        user10.setEmail("janek44@example.com");
        user10.setPassword("Janek4");
        userService.registerUser(user10);

        MyUser user11 = new MyUser();
        user11.setUsername("kasia21");
        user11.setEmail("kasia21@example.com");
        user11.setPassword("Kasia2");
        userService.registerUser(user11);

        MyUser user12 = new MyUser();
        user12.setUsername("lukasz88");
        user12.setEmail("lukasz88@example.com");
        user12.setPassword("Lukas7z");
        userService.registerUser(user12);

        MyUser user13 = new MyUser();
        user13.setUsername("magda77");
        user13.setEmail("magda77@example.com");
        user13.setPassword("Magda7");
        userService.registerUser(user13);

        MyUser user14 = new MyUser();
        user14.setUsername("natalia5");
        user14.setEmail("natalia5@example.com");
        user14.setPassword("Natal1a");
        userService.registerUser(user14);

        MyUser user15 = new MyUser();
        user15.setUsername("olaf22");
        user15.setEmail("olaf22@example.com");
        user15.setPassword("Olaf2022");
        userService.registerUser(user15);

        MyUser user16 = new MyUser();
        user16.setUsername("pawel90");
        user16.setEmail("pawel90@example.com");
        user16.setPassword("PaweL9");
        userService.registerUser(user16);

        MyUser user17 = new MyUser();
        user17.setUsername("rafal77");
        user17.setEmail("rafal77@example.com");
        user17.setPassword("Rafal7");
        userService.registerUser(user17);

        MyUser user18 = new MyUser();
        user18.setUsername("sylwia20");
        user18.setEmail("sylwia20@example.com");
        user18.setPassword("Sylwia2");
        userService.registerUser(user18);

        MyUser user19 = new MyUser();
        user19.setUsername("tomek33");
        user19.setEmail("tomek33@example.com");
        user19.setPassword("Tomek3");
        userService.registerUser(user19);

        MyUser user20 = new MyUser();
        user20.setUsername("urszula4");
        user20.setEmail("urszula4@example.com");
        user20.setPassword("Urszu4");
        userService.registerUser(user20);

        MyUser user21 = new MyUser();
        user21.setUsername("viktor56");
        user21.setEmail("viktor56@example.com");
        user21.setPassword("Viktor5");
        userService.registerUser(user21);

        MyUser user22 = new MyUser();
        user22.setUsername("weronika88");
        user22.setEmail("weronika88@example.com");
        user22.setPassword("Weron8");
        userService.registerUser(user22);

        MyUser user23 = new MyUser();
        user23.setUsername("xawery99");
        user23.setEmail("xawery99@example.com");
        user23.setPassword("Xawery9");
        userService.registerUser(user23);

        MyUser user24 = new MyUser();
        user24.setUsername("zofia66");
        user24.setEmail("zofia66@example.com");
        user24.setPassword("Zofia6");
        userService.registerUser(user24);


        MyPost post1 = new MyPost();
        post1.setUser(user);
        List<MyPostTag> tags = new ArrayList<>();
        tagService.findOrCreateTag("psy");
        tags.add(tagService.findOrCreateTag("psy"));
        tagService.findOrCreateTag("koty");
        tags.add(tagService.findOrCreateTag("koty"));
        post1.setTags(tags);
        post1.setTitle("Ciekawostka o psach i kotach");
        post1.setContent("Psy, znane ze swojej bliskości z ludźmi, komunikują się za pomocą szczekania, merdania ogonem i różnych postaw ciała. Ciekawie, koty wydają się miauczeć głównie do komunikacji z ludźmi, a nie z innymi kotami. Badania wykazały, że miauczenie kotów często jest sposobem na przyciągnięcie uwagi ludzi, a ich miauczenie może się zmieniać w zależności od tego, co próbują przekazać. Innymi słowy, psy i koty na różne sposoby dostosowały swoje metody komunikacji, aby lepiej współpracować i współistnieć z ludźmi.");
        post1.setDate(LocalDateTime.now());
        postService.savePost(post1);

        MyPost post2 = new MyPost();
        post2.setUser(user2);
        List<MyPostTag> tags2 = new ArrayList<>();
        tagService.findOrCreateTag("wegiel");
        tags2.add(tagService.findOrCreateTag("wegiel"));
        tagService.findOrCreateTag("spalanie");
        tags2.add(tagService.findOrCreateTag("spalanie"));
        post2.setTags(tags);
        post2.setTitle("Ciekawostka o spalaniu wegla");
        post2.setContent("Ciekawostką dotyczącą spalania węgla jest to, że proces ten jest jednym z głównych źródeł emisji dwutlenku węgla (CO2) na świecie, co znacząco przyczynia się do globalnego ocieplenia i zmian klimatycznych. Co interesujące, węgiel zawiera również naturalne radioizotopy, takie jak uran i tor, które są uwolnione do środowiska podczas spalania. Te radioaktywne elementy, choć występują w bardzo małych ilościach, gromadzą się w popiele węglowym – odpadzie powstającym po spalaniu węgla. Popiół ten jest jednym z największych strumieni odpadów przemysłowych na świecie i jego składowanie oraz utylizacja stanowią poważne wyzwanie środowiskowe. Pomimo tych negatywnych skutków, węgiel od dawna był i nadal jest ważnym źródłem energii, szczególnie w krajach rozwijających się, ze względu na jego obfitość i niski koszt.");
        post2.setDate(LocalDateTime.now());
        postService.savePost(post2);

        MyPost post3 = new MyPost();
        post3.setUser(user3);
        List<MyPostTag> tags3 = new ArrayList<>();
        tagService.findOrCreateTag("ośmiornica");
        tags3.add(tagService.findOrCreateTag("ośmiornica"));
        post3.setTags(tags);
        post3.setTitle("Ciekawostka o ośmiornicach");
        post3.setContent("Czy wiesz, że ośmiornice mają trzy serca? Dwa serca pompują krew do skrzeli, natomiast trzecie serce odpowiada za krążenie krwi do reszty ciała. Co więcej, krew ośmiornic zawiera białko o nazwie hemocyjanina, która zawiera miedź i nadaje jej niebieski kolor, w przeciwieństwie do hemoglobiny w ludzkiej krwi, która zawiera żelazo i jest czerwona.");
        post3.setDate(LocalDateTime.now());
        postService.savePost(post3);
    }
}
