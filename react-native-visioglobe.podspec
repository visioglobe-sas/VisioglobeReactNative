require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-visioglobe"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => "10.0" }
  s.source       = { :git => "https://github.com/.git", :tag => "#{s.version}" }

  
  s.source_files = "ios/**/*.{h,m,mm,swift}"
  s.exclude_files = "ios/VisioMoveEssential.xcframework/**/*.{h,hpp}" 
  s.preserve_paths = 'ios/VisioMoveEssential.xcframework'
  s.public_header_files = 'ios/VisioMoveEssential.xcframework/Versions/A/Headers/.{h,hpp}'
  s.vendored_frameworks = 'ios/VisioMoveEssential.xcframework'

  s.dependency "React-Core"
end
