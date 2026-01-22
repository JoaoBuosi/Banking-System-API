@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String token = JwtUtil.gerarToken(body.get("username"));
        return Map.of("token", token);
    }
}
