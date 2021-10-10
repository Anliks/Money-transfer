package com.anliks.transaction.controlers;


import com.anliks.transaction.models.Post;
import com.anliks.transaction.repo.PostRepository;
import com.anliks.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TransferController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/transfer")
    public String TransferMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "transfer";
    }

    @GetMapping("/transfer/add")
    public String TransferAdd(Model model) {
        return "transfer-add";
    }

    @PostMapping("/transfer/add")
    public String transferPostAdd(@RequestParam String name, @RequestParam double bank_account, @RequestParam int number_ofOperations, Model model) {
        Post post = new Post(name, bank_account, number_ofOperations);
        postRepository.save(post);
        return "redirect:/transfer";
    }

    @GetMapping("/transfer/{id}")
    public String TransferDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/transfer";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "transfer-details";
    }

    @GetMapping("/transfer/{id}/edit")
    public String TransferEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/transfer";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "transfer-edit";
    }
    @PostMapping("/transfer/{id}/edit")
    public String transferUpdate(@PathVariable(value = "id") long id,@RequestParam String name, @RequestParam double bank_account, @RequestParam int number_ofOperations, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setName(name);
        post.setBank_account(bank_account);
        post.setNumber_ofOperations(number_ofOperations);
        postRepository.save(post);
        return "redirect:/transfer";
    }
    @PostMapping("/transfer/{id}/remove")
    public String transferDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
      postRepository.delete(post);
        return "redirect:/transfer";
    }
   @GetMapping("/Balance")
   public String BalancePageOne(Model model) {
       Iterable<Post> posts = postRepository.findAll();
       model.addAttribute("posts", posts);
       return "Balance";
   }
   @GetMapping("/Balance/{id}")
    public String balanceDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);

       Iterable<Post> posts = postRepository.findAll();
       model.addAttribute("posts", posts);
        return "BalanceDetails";

   }
    @Transactional
   @PostMapping("/Balance/{id}/to")
    public String BalanceTransfer(@PathVariable(value = "id") long valueOf, @RequestParam long name, @RequestParam double bank_account, Model model ) {
       Post post = postRepository.findById(valueOf).orElseThrow();
       Post post1 = postRepository.findById(name).orElseThrow();
       post.setBank_account(post.getBank_account() - bank_account);
       post1.setBank_account(post1.getBank_account() + bank_account);
       postRepository.save(post);
       postRepository.save(post1);
        return "redirect:/transfer";
   }
    @GetMapping("/Balance/{id}/to")
    public String BalanceTransfers(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/transfer";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "Balancetransfer";
    }
}