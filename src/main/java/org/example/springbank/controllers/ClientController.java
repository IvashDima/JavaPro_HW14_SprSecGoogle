package org.example.springbank.controllers;

import org.example.springbank.models.Client;
import org.example.springbank.services.ClientService;
import org.example.springbank.services.DemoDataService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {

    static final int ITEMS_PER_PAGE = 5;
    private final ClientService clientService;
    private final DemoDataService demoDataService;

    public ClientController(ClientService clientService, DemoDataService demoDataService){
        this.clientService = clientService;
        this.demoDataService = demoDataService;
    }

    @GetMapping("/client/")
    public String index(Model model,
                        @RequestParam(required = false, defaultValue = "0") Integer page){
        if(page < 0) page = 0;

        List<Client> clients = clientService.findAll(PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("clients", clients);
        model.addAttribute("allPages", getPageCount());
        return "/client/index";
    }

    @GetMapping("/client/client_add_page")
    public String clientAddPage() {
        return "client/client_add_page";
    }

    @PostMapping(value="/client/add")
    public String clientAdd(@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String phone,
                            @RequestParam String email)
    {
        Client client = new Client(name, surname, phone, email);
        clientService.addClient(client);

        return "redirect:/client/";
    }

    @GetMapping("/client/reset")
    public String resetDemoData() {
        demoDataService.generateDemoData();
        return "redirect:/client/";
    }

    @PostMapping(value = "/client/delete")
    public ResponseEntity<Void> delete(
            @RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            clientService.deleteClient(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/client/search")
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("clients", clientService.findByPattern(pattern, null));

        return "client/index";
    }

    private long getPageCount() {
        long totalCount = clientService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
