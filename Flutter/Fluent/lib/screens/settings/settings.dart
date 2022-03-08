import 'package:fluent_ui/fluent_ui.dart';
import 'package:provider/provider.dart';

import 'package:fluent/provider/config.dart';
import 'package:fluent/provider/theme.dart';

const List<String> accentColorNames = [
  'System',
  'Yellow',
  'Orange',
  'Red',
  'Magenta',
  'Purple',
  'Blue',
  'Teal',
  'Green',
];

class Settings extends StatefulWidget {
  const Settings({Key? key, this.controller}) : super(key: key);

  final ScrollController? controller;

  @override
  State<Settings> createState() => _SettingsState();
}

class _SettingsState extends State<Settings> {
  bool _showPassword = false;

  @override
  Widget build(BuildContext context) {
    final appTheme = context.watch<AppTheme>();
    final tooltipThemeData = TooltipThemeData(decoration: () {
      const radius = BorderRadius.zero;
      final shadow = [
        BoxShadow(
          color: Colors.black.withOpacity(0.2),
          offset: const Offset(1, 1),
          blurRadius: 10.0,
        ),
      ];
      final border = Border.all(color: Colors.grey[100], width: 0.5);
      if (FluentTheme.of(context).brightness == Brightness.light) {
        return BoxDecoration(
          color: Colors.white,
          borderRadius: radius,
          border: border,
          boxShadow: shadow,
        );
      } else {
        return BoxDecoration(
          color: Colors.grey,
          borderRadius: radius,
          border: border,
          boxShadow: shadow,
        );
      }
    }());
    const spacer = SizedBox(height: 10.0);
    const biggerSpacer = SizedBox(height: 40.0);

    final svnPathController = TextEditingController();
    final svnUsernameController = TextEditingController();
    final svnPasswordController = TextEditingController();
    final mysqlPathController = TextEditingController();
    final mysqlUsernameController = TextEditingController();
    final mysqlPasswordController = TextEditingController();

    return ChangeNotifierProvider(
      create: (_) => Config(),
      builder: (context, _) {
        final config = context.watch<Config>();

        svnPathController.text = config.svnPath;
        svnUsernameController.text = config.svnUsername;
        svnPasswordController.text = config.svnPassword;
        mysqlPathController.text = config.mysqlPath;
        mysqlUsernameController.text = config.mySqlUsername;
        mysqlPasswordController.text = config.mysqlPassword;

        return ScaffoldPage.scrollable(
          header: const PageHeader(title: Text('Settings')),
          scrollController: widget.controller,
          children: [
            Text('SubVersion',
                style: FluentTheme.of(context).typography.subtitle),
            spacer,
            Row(
              children: [
                Expanded(
                  child: TextBox(
                    header: 'User Name',
                    placeholder: "Type your user name like 'seongha.moon'",
                    controller: svnUsernameController,
                    textInputAction: TextInputAction.next,
                    prefix: const Padding(
                      padding: EdgeInsetsDirectional.only(start: 8.0),
                      child: Icon(FluentIcons.people),
                    ),
                    outsideSuffix: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 4),
                      child: SizedBox(
                        width: 80,
                        child: Button(
                          child: const Text('Done'),
                          onPressed: () {
                            config.svnUsername = svnUsernameController.text;
                            showSnackbar(
                              context,
                              const Snackbar(
                                content: Text('Username 저장 완료!'),
                              ),
                              alignment: Alignment.bottomRight,
                            );
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                const SizedBox(width: 40),
                Expanded(
                  child: TextBox(
                    header: 'Password',
                    placeholder: 'Type your Password',
                    controller: svnPasswordController,
                    obscureText: !_showPassword,
                    maxLines: 1,
                    suffixMode: OverlayVisibilityMode.always,
                    suffix: IconButton(
                      icon: Icon(
                        !_showPassword ? FluentIcons.lock : FluentIcons.unlock,
                      ),
                      onPressed: () =>
                          setState(() => _showPassword = !_showPassword),
                    ),
                    outsideSuffix: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 4),
                      child: SizedBox(
                        width: 80,
                        child: Button(
                          child: const Text('Done'),
                          onPressed: () {
                            config.svnPassword = svnPasswordController.text;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
            spacer,
            TextBox(
              header: 'Subversion path',
              placeholder: "C:\\Program Files (x86)\\Subversion\\bin",
              controller: svnPathController,
              readOnly: true,
              textInputAction: TextInputAction.next,
              prefix: const Padding(
                padding: EdgeInsetsDirectional.only(start: 8.0),
                child: Icon(FluentIcons.people),
              ),
              outsideSuffix: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 4),
                child: SizedBox(
                  width: 80,
                  child: Button(
                    child: const Text('Change'),
                    onPressed: () {},
                  ),
                ),
              ),
            ),
            biggerSpacer,
            Text('Mysql', style: FluentTheme.of(context).typography.subtitle),
            spacer,
            Row(
              children: [
                Expanded(
                  child: TextBox(
                    header: 'User Name',
                    placeholder: "Type your user name like 'root'",
                    controller: mysqlUsernameController,
                    textInputAction: TextInputAction.next,
                    prefix: const Padding(
                      padding: EdgeInsetsDirectional.only(start: 8.0),
                      child: Icon(FluentIcons.people),
                    ),
                    outsideSuffix: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 4),
                      child: SizedBox(
                        width: 80,
                        child: Button(
                          child: const Text('Done'),
                          onPressed: () {
                            config.mysqlUsername = mysqlUsernameController.text;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                const SizedBox(width: 40),
                Expanded(
                  child: TextBox(
                    header: 'Password',
                    placeholder: 'Type your Password',
                    controller: mysqlPasswordController,
                    obscureText: !_showPassword,
                    maxLines: 1,
                    suffixMode: OverlayVisibilityMode.always,
                    suffix: IconButton(
                      icon: Icon(
                        !_showPassword ? FluentIcons.lock : FluentIcons.unlock,
                      ),
                      onPressed: () =>
                          setState(() => _showPassword = !_showPassword),
                    ),
                    outsideSuffix: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 4),
                      child: SizedBox(
                        width: 80,
                        child: Button(
                          child: const Text('Done'),
                          onPressed: () {
                            config.mysqlPassword = mysqlPasswordController.text;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
            spacer,
            Expanded(
              child: TextBox(
                header: 'MySql path',
                placeholder: "C:\\Program Files\\MariaDB 10.5\\bin",
                controller: mysqlPathController,
                readOnly: true,
                textInputAction: TextInputAction.next,
                prefix: const Padding(
                  padding: EdgeInsetsDirectional.only(start: 8.0),
                  child: Icon(FluentIcons.people),
                ),
                outsideSuffix: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 4),
                  child: SizedBox(
                    width: 80,
                    child: Button(
                      child: const Text('Change'),
                      onPressed: () {},
                    ),
                  ),
                ),
              ),
            ),
            biggerSpacer,
            Text('Theme mode',
                style: FluentTheme.of(context).typography.subtitle),
            spacer,
            ...List.generate(ThemeMode.values.length, (index) {
              final mode = ThemeMode.values[index];
              return Padding(
                padding: const EdgeInsets.only(bottom: 8.0),
                child: RadioButton(
                  checked: appTheme.mode == mode,
                  onChanged: (value) {
                    if (value) {
                      appTheme.mode = mode;
                    }
                  },
                  content: Text('$mode'.replaceAll('ThemeMode.', '')),
                ),
              );
            }),
            biggerSpacer,
            Text(
              'Navigation Pane Display Mode',
              style: FluentTheme.of(context).typography.subtitle,
            ),
            spacer,
            ...List.generate(PaneDisplayMode.values.length, (index) {
              final mode = PaneDisplayMode.values[index];
              return Padding(
                padding: const EdgeInsets.only(bottom: 8.0),
                child: RadioButton(
                  checked: appTheme.displayMode == mode,
                  onChanged: (value) {
                    if (value) appTheme.displayMode = mode;
                  },
                  content: Text(
                    mode.toString().replaceAll('PaneDisplayMode.', ''),
                  ),
                ),
              );
            }),
            biggerSpacer,
            Text('Navigation Indicator',
                style: FluentTheme.of(context).typography.subtitle),
            spacer,
            ...List.generate(NavigationIndicators.values.length, (index) {
              final mode = NavigationIndicators.values[index];
              return Padding(
                padding: const EdgeInsets.only(bottom: 8.0),
                child: RadioButton(
                  checked: appTheme.indicator == mode,
                  onChanged: (value) {
                    if (value) appTheme.indicator = mode;
                  },
                  content: Text(
                    mode.toString().replaceAll('NavigationIndicators.', ''),
                  ),
                ),
              );
            }),
            biggerSpacer,
            Text('Accent Color',
                style: FluentTheme.of(context).typography.subtitle),
            spacer,
            Wrap(children: [
              Tooltip(
                style: tooltipThemeData,
                child: _buildColorBlock(appTheme, systemAccentColor),
                message: accentColorNames[0],
              ),
              ...List.generate(Colors.accentColors.length, (index) {
                final color = Colors.accentColors[index];
                return Tooltip(
                  style: tooltipThemeData,
                  message: accentColorNames[index + 1],
                  child: _buildColorBlock(appTheme, color),
                );
              }),
            ]),
          ],
        );
      },
    );
  }

  Widget _buildColorBlock(AppTheme appTheme, AccentColor color) {
    return Padding(
      padding: const EdgeInsets.all(2.0),
      child: Button(
        onPressed: () {
          appTheme.color = color;
        },
        style: ButtonStyle(padding: ButtonState.all(EdgeInsets.zero)),
        child: Container(
          height: 40,
          width: 40,
          color: color,
          alignment: Alignment.center,
          child: appTheme.color == color
              ? Icon(
                  FluentIcons.check_mark,
                  color: color.basedOnLuminance(),
                  size: 22.0,
                )
              : null,
        ),
      ),
    );
  }
}
